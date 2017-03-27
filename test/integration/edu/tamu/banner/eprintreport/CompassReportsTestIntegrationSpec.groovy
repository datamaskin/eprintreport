package edu.tamu.banner.eprintreport

import com.google.gson.Gson
import edu.tamu.compassreport.WriteBlob
import grails.converters.JSON
import grails.test.spock.IntegrationSpec

import java.util.logging.Logger


/**
 * Created by datamaskinaggie on 10/31/16.
 */
class CompassReportsTestIntegrationSpec extends IntegrationSpec {

    CompassReportsService compassReportsService
    def dataSource


    void "Fetch GwRpts reports using the report name" () {
        when:
        def name = 'TSRCBIL'

        then:
        name != null
        def reports = compassReportsService.getCompassReportsAsJSON(name)
        Gson gson = new Gson()
        def json = gson.toJson(reports)
        assert json
        assert reports
        Logger.getLogger("CompassReportsService").info("Reports: " + json)
    }

    void "Fetch GwRpts reports using the sequence number" () {
        when:
        BigInteger seq = 51;

        then:
        seq != null;

        def report = compassReportsService.getGwRptsBlobAsJSON(seq)
        /*Gson gson = new Gson()
        def json = gson.toJson(report)
        assert json*/
        assert report
        Logger.getLogger("CompassReportsService").info("Report: " + report)
    }

//    Next test only runs in DEVL
    /*void "Fetch GwRpts blob bytes as byte array using a Java class" () {
        when:
        def filename = "21.pdf"
        WriteBlob writeBlob = new WriteBlob()
        writeBlob.setDataSource(dataSource)

        then:
        writeBlob != null
        def len = compassReportsService.writeBlobToFile(filename)
        if (len <= 0) {
            Logger.getLogger("WriteBlob failed: " + len)
            println "WriteBlob failed: ${len}"
        } else {
            Logger.getLogger("WriteBlob success: " + len)
            println "WriteBlob success: ${len}"
        }
    }*/

//    This test only runs in TSTX
    /*void "Test the PL/SQL function: GWK_COMPASS_REPORTS.F_GET_REPORT_INFO with a report name" () {
        when:
        def name = 'SFRPINI'

        then:
        name != null
        def reportinfo = compassReportsService.getReportInfoJSON(name)
        assert reportinfo
        Logger.getLogger("CompassReportService").info("Report data: ${reportinfo}")
    }

    void "Test the PL/SQL (MH) function: GWK_COMPASS_REPORTS.F_GET_RPTS_DEF to replace the current raw SQL select used to fetch GW_RPTS_DEF" () {
        when:
        def reportdef = compassReportsService.getReportDefJSON()

        then:
        reportdef != null
        assert reportdef
        Logger.getLogger("CompassReportService").info("Report data: ${reportdef}")
    }*/
}