package edu.tamu.banner.eprintreport

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.test.spock.IntegrationSpec
import groovy.json.JsonSlurper
import org.apache.log4j.Logger
import java.util.logging.Logger
import org.codehaus.groovy.grails.web.json.JSONObject

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
        rptsDef.gwRptsDefObjectName = "GURPDED"

        RestBuilder restBuilder = new RestBuilder()

        restBuilder.restTemplate.messageConverters.removeAll {
            it.class.name == 'org.springframework.http.converter.json.GsonHttpMessageConverter'
        }

        when:
        RestResponse response = restBuilder.get("http://localhost:8080/EprintReport/GwRptsDef") {
            contentType "application/vnd.org.jfrog.artifactory.security.Group+json"
            accept JSONObject, 'application/json'
        }

        response.json instanceof JSONObject
//        def list = new JsonSlurper().parseText(response.text)
        response.json.gwRptsDefObjectName == 'GURPDED'

        *//*def result = list["gwRptsDefObjectName"]

        List names = new ArrayList()
        names.addAll(result)

        names.each {
            println(it)
        }*//*

        then:
        assert response.status == 200
        assert response.json != null
        assert response.text != null
        assert result != null
        assert names != null
        assert response.text.size() > 0

    }*/

    /*void "Fetch domain GwRpts properties from the REST endpoint" () {
        given:
        GwRpts rpts = new GwRpts()
        rpts.gwRptsSequence = 55

        RestBuilder restBuilder = new RestBuilder()

        restBuilder.restTemplate.messageConverters.removeAll {
            it.class.name == 'org.springframework.http.converter.json.GsonHttpMessageConverter'
        }

        when:
        RestResponse response = restBuilder.get("http://localhost:8080/EprintReport/GwRpts") {
            contentType "application/vnd.org.jfrog.artifactory.security.Group+json"
            accept JSONObject, 'application/json'
        }

        def list = new JsonSlurper().parseText(response.text)

        def result = list["gwRptsSequence"]

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

    void "Fetch GwRpts reports in a join with GwRptsDef" () {
        when:
        def name = 'GURPDED'

        then:
        name != null
        def reports = compassReportsService.getCompassReports(name)
//        Logger.getLogger("CompassReportsService").info("Reports: " + reports.get(0).toString())
        Logger.getLogger("CompassReportsService").info("Reports: " + reports)
        println reports
    }

    void "Fetch Student PIDM with UIN" () {
        when:
        def uin = '826000013'

        then:
        uin != null
        def pidm = compassReportsService.getStudentPidmUIN(uin)
        Logger.getLogger("PIDM: " + pidm)
    }

}