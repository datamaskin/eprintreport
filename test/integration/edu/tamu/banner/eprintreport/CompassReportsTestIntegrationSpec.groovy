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

//    Next 2 tests only runs in DEVL
    void "Fetch GwRpts reports using the report name" () {
        when:
        def name = 'SFRFEES'

        then:
        name != null
        def reports = compassReportsService.getCompassReportsAsJSON(name)
        Gson gson = new Gson()
        def json = gson.toJson(reports)
        assert json
        assert reports
        Logger.getLogger("CompassReportsService").info("Reports: " + reports)
    }

    void "Fetch GwRpts blob bytes as byte array using a Java class" () {
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
    }

//    This test only runs in TSTX
    void "Test the PL/SQL function: GWK_COMPASS_REPORTS.F_GET_REPORT_INFO with a report name" () {
        when:
        def name = 'SFRPINI'

        then:
        name != null
        def reportinfo = compassReportsService.getReportInfoJSON(name)
        assert reportinfo
        Logger.getLogger("CompassReportService").info("Report data: ${reportinfo}")
    }
}