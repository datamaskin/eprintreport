package edu.tamu.banner.eprintreport

import com.google.gson.Gson
import grails.converters.JSON

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GwRptsDefController {

    def compassReportsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {

//        params.max = Math.min(max ?: 10, 100)

//        edu.tamu.eis.GwRptsDef gwRptsDef = new edu.tamu.eis.GwRptsDef()

        // Oracle version
//        List json = compassReportsService.getReportDefJSON()
        /*List json = compassReportsService.getCompassReportsAsJSON()
        gwRptsDef.gwRptsDefObjectName = json['gwRptsDefObjectName'].asList()
        gwRptsDef.gwRptsDefObjectDesc = json['gwRptsDefObjectDesc'].asList()*/
        /*def json = compassReportsService.getCompassReportsAsJSON()
        Gson gson = new Gson()
        List jList = gson.fromJson(json, List.class)
        gwRptsDef.gwRptsDefObjectName = jList['GW_RPTS_DEF_OBJECT_NAME'].asList()
        gwRptsDef.gwRptsDefObjectDesc = jList['GW_RPTS_DEF_OBJECT_DESC'].asList()
        gwRptsDef.gwRptsDefRetentionDays = jList['GW_RPTS_DEF_RETENTION_DAYS'].asList()

        String pattern = "dd-MM-yyyy hh:mm:ss"*/

//        List dates = jList['gwRptsDefActivityDate'].asList()
        /*List dates = jList['GW_RPTS_DEF_ACTIVITY_DATE'].asList()

        gwRptsDef.gwRptsDefActivityDate = dates

        def resultMap = [:]
        if (params.draw) {
            resultMap.draw = params.draw as int
        } else {
            resultMap.draw = params.max as int
        }
        resultMap.recordsTotal = jList.size()
        resultMap.recordsFiltered = jList.size()
        resultMap.data = [
                gwRptsDef.gwRptsDefObjectName,
                gwRptsDef.gwRptsDefObjectDesc,
                gwRptsDef.gwRptsDefRetentionDays,
                gwRptsDef.gwRptsDefActivityDate
        ]*/

//        respond gwRptsDef as JSON
//        render json

//        respond resultMap as JSON
//        render transpose as JSON
    }

    def show(GwRptsDef gwRptsDefInstance) {
        respond gwRptsDefInstance
    }

    def create() {
        respond new GwRptsDef(params)
    }

    @Transactional
    def save(GwRptsDef gwRptsDefInstance) {
        if (gwRptsDefInstance == null) {
            notFound()
            return
        }

        if (gwRptsDefInstance.hasErrors()) {
            respond gwRptsDefInstance.errors, view:'create'
            return
        }

        gwRptsDefInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gwRptsDef.label', default: 'GwRptsDef'), gwRptsDefInstance.id])
                redirect gwRptsDefInstance
            }
            '*' { respond gwRptsDefInstance, [status: CREATED] }
        }
    }

    def edit(GwRptsDef gwRptsDefInstance) {
        respond gwRptsDefInstance
    }

    def gwrptsReportDefJSON_H2() {
        edu.tamu.eis.GwRptsDef gwRptsDef = new edu.tamu.eis.GwRptsDef()

//        def json = compassReportsService.getCompassReportsAsJSON()
        def jList = compassReportsService.getCompassReportsAsJSON()
        /*Gson gson = new Gson()
        List jList = gson.fromJson(json, List.class)*/
        gwRptsDef.gwRptsDefObjectName = jList['GW_RPTS_DEF_OBJECT_NAME'].asList()
        gwRptsDef.gwRptsDefObjectDesc = jList['GW_RPTS_DEF_OBJECT_DESC'].asList()
        gwRptsDef.gwRptsDefRetentionDays = jList['GW_RPTS_DEF_RETENTION_DAYS'].asList()

        String pattern = "dd-MM-yyyy hh:mm:ss"

        List dates = jList['GW_RPTS_DEF_ACTIVITY_DATE'].asList()

        gwRptsDef.gwRptsDefActivityDate = dates

        params.max = 10
        def resultMap = [:]
        if (params.draw) {
            resultMap.draw = params.draw
        } else {
            resultMap.draw = params.max
        }
        resultMap.recordsTotal = jList.size()
        resultMap.recordsFiltered = jList.size()

        resultMap.data = [[null, gwRptsDef.gwRptsDefObjectName.get(0), gwRptsDef.gwRptsDefObjectDesc.get(0), gwRptsDef.gwRptsDefRetentionDays.get(0), gwRptsDef.gwRptsDefActivityDate.get(0)]]
        for (int i = 1; i < resultMap.recordsTotal; i++) {
            resultMap.data += [[null, gwRptsDef.gwRptsDefObjectName.get(i), gwRptsDef.gwRptsDefObjectDesc.get(i), gwRptsDef.gwRptsDefRetentionDays.get(i), gwRptsDef.gwRptsDefActivityDate.get(i)]]
        }
        /*resultMap.data = [
                gwRptsDef.gwRptsDefObjectName,
                gwRptsDef.gwRptsDefObjectDesc,
                gwRptsDef.gwRptsDefRetentionDays,
                gwRptsDef.gwRptsDefActivityDate
        ]*/

//        respond resultMap as JSON
        render resultMap as JSON
    }

    def gwrptsReportDefJSON() {
        def reportdef = compassReportsService.getReportDefJSON()
        render reportdef
    }

    @Transactional
    def update(GwRptsDef gwRptsDefInstance) {
        if (gwRptsDefInstance == null) {
            notFound()
            return
        }

        if (gwRptsDefInstance.hasErrors()) {
            respond gwRptsDefInstance.errors, view:'edit'
            return
        }

        gwRptsDefInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GwRptsDef.label', default: 'GwRptsDef'), gwRptsDefInstance.id])
                redirect gwRptsDefInstance
            }
            '*'{ respond gwRptsDefInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GwRptsDef gwRptsDefInstance) {

        if (gwRptsDefInstance == null) {
            notFound()
            return
        }

        gwRptsDefInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GwRptsDef.label', default: 'GwRptsDef'), gwRptsDefInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gwRptsDef.label', default: 'GwRptsDef'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
