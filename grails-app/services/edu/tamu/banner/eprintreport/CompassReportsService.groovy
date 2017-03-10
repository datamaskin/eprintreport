package edu.tamu.banner.eprintreport

import com.google.gson.Gson
import edu.tamu.compassreport.BLOBFile
import edu.tamu.compassreport.ReadBlob
import grails.converters.JSON
import grails.transaction.Transactional
import grails.util.Holders
import groovy.json.JsonSlurper
import groovy.sql.Sql
import org.apache.log4j.Logger
import org.springframework.context.ApplicationContext

import javax.sql.DataSource
import java.sql.Blob

@Transactional
class CompassReportsService {

    def dataSource
    def sessionFactory
    def log = Logger.getLogger(this.getClass())
    def grailsApplication

    def  getStudentPidmUIN(final String UIN) { // Fetch student PIDM using UIN

        final String query = """
                            select n.spriden_pidm
                            from spriden n
                            where n.spriden_id = :uin
                            and n.spriden_ntyp_code = 'UIN'
                            """

        final Sql sql = new Sql(dataSource: dataSource)
        final params = [uin: UIN]

        final results = sql.rows(query, params)
        results
    }

    def getCompassReportsAsJSON(final name) {
        final Sql sql = new Sql(dataSource: dataSource)

        log.debug("getCompassReportsAsJSON: ${name}")

        def stmt = """
                    select gw_rpts_sequence, gw_rpts_mime, gw_rpts_object_name, gw_rpts_create_date from gw_rpts_all where gw_rpts_object_name = :aName
                    """

        /*def stmt =  """
                    select BANINST1.GWK_COMPASS_REPORTS.F_GET_REPORT_INFO(:aName) from dual
                    """*/

        final params = [aName: name]

        final results = sql.rows(stmt, params)
        log.debug "getCompassReportsAsJSON: ${results}"
        results as JSON
    }

    def getGwRptsBlobAsJSON(final BigInteger seq) {
        final session = sessionFactory.currentSession
        final String query = """
                                select gw_rpts_object_name, gw_rpts_mime, gw_rpts_blob
                                from gw_rpts inner join gw_rpts_def
                                on gw_rpts.gw_rpts_sequence = :seq
                              """
        final sqlQuery = session.createSQLQuery(query)
        final queryResults = sqlQuery.with {
            setBigInteger('seq', seq)
            list()
        }
        final results = queryResults.collect { resultRow ->
            [gwRptsDefObjectName: resultRow]
        }

        results as JSON
    }

    def getGwRptsBlobPDFBytes(BigDecimal seq) {

        final session = sessionFactory.currentSession

        final String query = """
                                select gw_rpts_blob
                                from gw_rpts inner join gw_rpts_def
                                on gw_rpts.gw_rpts_sequence = :seq
                              """
        final sqlQuery = session.createSQLQuery(query)
        final queryResults = sqlQuery.with {
            setBigDecimal('seq', seq)
            list()
        }
        final results = queryResults.collect { resultRow ->
            [gwRptsBlob: resultRow].values()
        }

        Blob blob = results.get(0).toArray()[0]
        def byte_stream = blob.getBinaryStream()
        if (byte_stream == null) {
            println "Error for ${seq}"
            throw new Exception("Binary Stream exception: " + byte_stream)
        }
    }

    def writeBlobToFile(final String fileName) {
        def location = "/"+grailsApplication.config.EprintReport.file.storage.location
        def path = grailsApplication.mainContext.getResource(location).file
        def realpath = path.absolutePath
        BLOBFile blobFile = new BLOBFile(realpath+"/"+fileName)
        blobFile.openOracleConnection()
        def size = blobFile.readBLOBToFileGet(fileName)
        blobFile.closeOracleConnection()
        log.debug "writeBlobToFile: ${fileName} ${location}"
        size
    }

    byte[] readBlobToByte(final BigDecimal seq) {
        DataSource d = dataSource
        byte[] b = new ReadBlob().readBlob(seq, d)
        b
    }

    def getReportInfoJSON(String name){
        String jsontext
        def reportjson = null

        try {
            def sessionFactory
            if (!sessionFactory) {
                ApplicationContext ctx = (ApplicationContext) Holders.grailsApplication.getMainContext()
                log.debug("$ctx")
                sessionFactory = ctx.getBean('sessionFactory')
            }
            log.debug("$sessionFactory")

            def sql = new Sql(sessionFactory.getCurrentSession().connection())
            log.debug("$sql -- GWK_COMPASS_REPORTS.F_GET_REPORT_INFO call --")

            def stmt = '{call ? := GWK_COMPASS_REPORTS.F_GET_REPORT_INFO(?)}'
            def params = [Sql.VARCHAR, name]

            sql.call( stmt, params, { it ->
                jsontext = it
            })

            reportjson = new JsonSlurper().parseText(jsontext)
        }
        catch (Exception e) {
            e.printStackTrace()
        }
        reportjson
    }
}