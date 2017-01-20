package edu.tamu.banner.eprintreport

import grails.converters.JSON
import grails.transaction.Transactional
import grails.validation.Validateable
import groovy.json.JsonOutput
import groovy.json.StreamingJsonBuilder
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import oracle.sql.BLOB
import org.apache.log4j.Logger

import java.sql.Blob
import java.sql.Connection

@Transactional
class CompassReportsService {

    def groovySql
    def dataSource
    def sessionFactory
    def log = Logger.getLogger(this.getClass())

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

    def setCompassReport(String dir, String fileName, String reportsName) {
        final Sql sql = new Sql(dataSource: dataSource)

        log.debug("CompassReportsService start.")

        def stmt = '{call GWK_COMPASS_REPORTS.pws_insert_report(?, ?, ?)}'
        def params = [dir, fileName, reportsName]

        sql.call stmt, params

        log.debug("CompassReportsService end.")
    }

    def getCompassReportNames(final pidm_uin) {
        final Sql sql = new Sql(dataSource: dataSource)

        log.debug("getCompassReportNames start")

        def names = [:]

        def stmt = '{? = call GWK_COMPASS_REPORTS.fws_user_reports(?)}'
//        def params = [sql.resultSet(OracleTypes.CURSOR), pidm_uin]
        def params = [sql.REF, pidm_uin]
        def gwrpts = new GwRptsDef()
        sql.call(stmt, params, { cursor ->
            cursor.eachRow { result ->
//                gwrpts = new GwRptsDef(gwRptsDefObjectName: result.gw_rpts_def_object_name, gwRptsDefObjectDesc: result.gw_rpts_def_object_name)
                gwrpts = new GwRptsDef(gwRptsDefObjectName: result.gw_rpts_def_object_name)
//                log.debug("gw_rpts_def_object_name: ${gwrpts.gwRptsDefObjectName}", "gw_rpts_def_object_desc: ${gwrpts.gwRptsDefObjectDesc}")
//                names.put(gwrpts.gwRptsDefObjectName, gwrpts.gwRptsDefObjectDesc)
                names.put(gwrpts.gwRptsDefObjectName)
//                log.debug("${gwrpts.gwRptsDefObjectName} => ${names.get(gwrpts.gwRptsDefObjectDesc)}")
//                System.out.println(gwrpts.gwRptsDefObjectName)
            }
        })

        log.debug("getCompassReportNames end: ${names}")
        names
    }

    def getCompassReportsAsJSON(final name) {
        final Sql sql = new Sql(dataSource: dataSource)

        log.debug("getCompassReportsAsJSON")

        /*def stmt =  """
                    select gw_rpts_object_name, gw_rpts_mime, gw_rpts_sequence, gw_rpts_blob
                    from gw_rpts inner join gw_rpts_def
                    on gw_rpts.gw_rpts_object_name = :aName
                    """*/

        /*def stmt = """
                    select gw_rpts_sequence, gw_rpts_blob, gw_rpts_object_name from gw_rpts where gw_rpts_object_name = :aName
                    """*/

        def stmt = """
                    select gw_rpts_sequence, gw_rpts_mime, gw_rpts_object_name from gw_rpts where gw_rpts_object_name = :aName
                    """

        final params = [aName: name]

        final results = sql.rows(stmt, params) //TODO JSON is an issue
        results as JSON
//        results
        /*def json
        def sw = new StringWriter().with { w ->
            json = new StreamingJsonBuilder(w)
        }
        def result = json(results)

//        String json = JsonOutput.prettyPrint(sw.toString())
        result as JSON*/
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

        results
    }

    byte[] getGwRptsBlobBytes(final BigInteger seq) {
        final session = sessionFactory.currentSession

        final String query = """
                                select gw_rpts_blob
                                from gw_rpts inner join gw_rpts_def
                                on gw_rpts.gw_rpts_sequence = :seq
                              """

        final sqlQuery = session.createSQLQuery(query)
        final queryResults = sqlQuery.with {
            setBigInteger('seq', seq)
            list()
        }
        final results = queryResults.collect { resultRow ->
            [gwRptsBlob: resultRow].values()
        }

        Blob blob=results.get(0).toArray()[0]
        def byte_stream = blob.getBinaryStream()
        if( byte_stream == null ) {
            println "Error for ${seq}"
            throw new Exception("Binary Stream exception: " + byte_stream)
        }

        int total = blob.length();

        byte[] byte_array = new byte[total]
        byte_array
    }

    def getGwRptsBlobPDFBytes(final BigInteger seq) {

        final session = sessionFactory.currentSession

        final String query = """
                                select gw_rpts_blob
                                from gw_rpts inner join gw_rpts_def
                                on gw_rpts.gw_rpts_sequence = :seq
                              """

        final sqlQuery = session.createSQLQuery(query)
        final queryResults = sqlQuery.with {
            setBigInteger('seq', seq)
            list()
        }
        final results = queryResults.collect { resultRow ->
            [gwRptsBlob: resultRow].values()
        }

        results
    }

    def writeBlobToFile(final seq, final String filename, final String directory, boolean reallyWrite=false) {

        def results = getGwRptsBlobPDFBytes(seq)
        Blob blob=results.get(0).toArray()[0]
        def byte_stream = blob.getBinaryStream()
        if( byte_stream == null ) {  println "Error for ${seq}"  }

        int total = blob.length();
        if (filename == null)
            reallyWrite = false

// Write to a file
        if (reallyWrite) {
            byte[] byte_array = new byte[total]
            int bytes_read = byte_stream.read(byte_array)
            def fullname = ""
            if (directory == null) {
                fullname = "$filename"
            } else if (directory !=null && !directory.endsWith('/')) {
                fullname = "$directory/$filename"
            } else fullname = "$directory$filename"
            def fos= new FileOutputStream(fullname)
            fos.write(byte_array);
            fos.close()
        }
        println "Document $seq: file: $filename, size $total"
        return total
    }

    static String toCamelCase(String val){
        def result = val.split("_").collect { word ->
            word[0].toUpperCase() + word[1..-1].toLowerCase()
        }.join("")
        result[0].toLowerCase() + result[1..-1]
    }

    Collection getData(String query, Class clazz){
        def field = ''
        def result = []
        final Sql sql = new Sql(dataSource: dataSource)
        sql.rows(query).each {row ->
            def dto = clazz.newInstance()
            row.keySet().each{
                field = toCamelCase(it)
                dto."${field}" = row."${it}"
            }
            result << dto
        }
        result
    }

}