package edu.tamu.banner.eprintreport

import grails.converters.JSON
import grails.plugin.cache.web.filter.PageFragmentCachingFilter
import grails.test.spock.IntegrationSpec
import groovy.json.JsonOutput

import java.util.logging.Logger
/**
 * Created by datamaskinaggie on 10/31/16.
 */
class CompassReportsTestIntegrationSpec extends IntegrationSpec {

    CompassReportsService compassReportsService

    /*void "Call pws_insert_report to upload a diskfile to the DB as a Blob" () {
        when:
        String dir = "/Users/datamaskinaggie/Documents/misc/"
        String filename ="cheatsheet.pdf"
        String objname = "cheatsheet"

        then:
        dir != null
        compassReportsService.setCompassReport(dir, filename, objname)
        Logger.getLogger(this.class).debug("CompassReports Test Integration pws_insert_report")
    }*/

    /*void "Call fws_user_reports to return a list of reports using the user UIN or PIDM to select" () {
        when:
        def pidm = compassReportsService.getStudentPidmUIN('105008316')

        then:
        pidm != null
        def ref = compassReportsService.getCompassReportNames(pidm)
        assert ref != null
        Logger.getLogger(this.class).debug("CompassReports Test Integration fws_user_reports: ${ref.get(0)}")
    }*/

    /*void "Fetch domain GwRptsDef properties from the REST endpoint" () {
        given:
        GwRptsDef rptsDef = new GwRptsDef()
        rptsDef.gwRptsDefObjectName = "GZRTCCV"

        RestBuilder restBuilder = new RestBuilder()

        restBuilder.restTemplate.messageConverters.removeAll {
            it.class.name == 'org.springframework.http.converter.json.GsonHttpMessageConverter'
        }

        when:
        RestResponse response = restBuilder.get("http://localhost:8080/eprint/GwRptsDef") {
            contentType "application/vnd.org.jfrog.artifactory.security.Group+json"
            accept JSONObject, 'application/json'
        }

        def list = new JsonSlurper().parseText(response.text)

        def result = list["gwRptsDefObjectName"]

        List names = new ArrayList()
        names.addAll(result)

        names.each {
            println(it)
        }

        then:
        assert response.status == 200
        assert response.json != null
        assert response.text != null
        assert result != null
        assert names != null
        assert response.text.size() > 0

    }*/

    void "Fetch GwRpts reports using the report name" () {
        when:
        def name = 'TGPHOLD'

        then:
        name != null
        def reports = compassReportsService.getCompassReportsAsJSON(name)
        Logger.getLogger("CompassReportsService").info("Reports: " + reports)

        reports.each {
//            println "Report: ${it.get('GW_RPTS_BLOB') as JSON}" // blows up
            println "Report: ${it.writer}"
        }
    }

    void "Fetch GwRpts blobs using hibernate native SQL query" () {
        when:
        BigInteger seq = 364

        then:
        seq != null
        def report = compassReportsService.getGwRptsBlobAsJSON(seq)
        Logger.getLogger("CompassReportsService").info("Blob: " + report.get(0))

        report.each {
            println "Report: ${it.values()}"
        }

    }

    void "Fetch GwRpts blob bytes as byte array using hibernate native SQL query" () {
        when:
        BigInteger seq = 364

        then:
        seq != null
        def report = compassReportsService.getGwRptsBlobPDFBytes(seq)
        Logger.getLogger("CompassReportsService").info("Byte array: " + report.get(0))

        report.each {
            println "Report: ${it}"
        }
    }

    void "Write PDF Blob data as PDF disk file" () {
        when:
        BigInteger seq = 364

        then:
        seq != null
        def report = compassReportsService.writeBlobToFile(seq, "tgphold_seq_364.pdf", "/Users/datamaskinaggie/tmp/Blob", true)
        Logger.getLogger("CompassReportsService").info("Report Blob: " + report.byteValue())
        report.each {
            println "Report PDF file disk write: ${it}"
        }
    }

    void "Test Service method getData() to populate GwRpts domain" () {
        when:
        final String query = """
                                select gw_rpts_object_name, gw_rpts_blob
                                from gw_rpts inner join gw_rpts_def
                                on gw_rpts.gw_rpts_sequence = '364'
                              """
        final GwRpts gwRpts = new GwRpts()

        then:
        query != null
        def report = compassReportsService.getData(query, gwRpts.class)

        report.each {
            println "Report: ${it}"
        }
    }

    void "Test fetching Blobs as byte[] array" () {
        when:
        final BigInteger seq = 364

        then:
        seq != null
        def report = compassReportsService.getGwRptsBlobBytes(seq)
        Logger.getLogger("CompassReportsService").info("Report Blob: " + report.toList())

        /*report.each {
            println "Report: ${it.toString()}"
        }*/
        report.toList()
    }
}