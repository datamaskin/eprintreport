package edu.tamu.banner.eprintreport

import edu.tamu.compassreport.WriteBlob
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

    void "Fetch GwRpts blob bytes as byte array using a Java class" () {
        when:
        BigInteger seq = 88
        WriteBlob writeBlob = new WriteBlob()
        writeBlob.setDataSource(dataSource)

        then:
        writeBlob != null
        def len = compassReportsService.writeBlobToFile(seq, writeBlob)
        if (len <= 0) {
            Logger.getLogger("WriteBlob failed: " + len)
            println "WriteBlob failed: ${len}"
        } else {
            Logger.getLogger("WriteBlob success: " + len)
            println "WriteBlob success: ${len}"
        }
    }
}