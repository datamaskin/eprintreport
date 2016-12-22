package edu.tamu.banner.eprintreport

import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GwRptsController {

    def compassReportsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
//        response.contentType = "application/json"
        params.max = Math.min(max ?: 10, 100)
        respond GwRpts.list(params), model:[gwRptsInstanceCount: GwRpts.count()]
    }

    def show(GwRpts gwRptsInstance) {
        respond gwRptsInstance
    }

    def create() {
        respond new GwRpts(params)
    }

    def gwrptsToJSON(final String report) {
//        response.contentType = "application/json"
        def gwrpts = GwRpts.findAllByGwRptsObjectName(report).toList()
        def result = [gwRptsInstance: gwrpts]
//        render "Params: report = $report"
        render gwrpts as JSON
//        def result = compassReportsService.getCompassReports('GURPDED')
    }

    def gwrptsSeqNameBlob(final String name) { // http://localhost:8080/EprintReport/gwrptsSNB?name=tgrfeed
        def gwrtps = compassReportsService.getCompassReports(name)
        render gwrtps
    }

    def gwrptsBlob(final long seq) {
        def gwrpts = compassReportsService.getGwRptsBlob(seq)
        render gwrpts
    }

    @Transactional
    def save(GwRpts gwRptsInstance) {
        if (gwRptsInstance == null) {
            notFound()
            return
        }

        if (gwRptsInstance.hasErrors()) {
            respond gwRptsInstance.errors, view:'create'
            return
        }

        gwRptsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gwRpts.label', default: 'GwRpts'), gwRptsInstance.id])
                redirect gwRptsInstance
            }
            '*' { respond gwRptsInstance, [status: CREATED] }
        }
    }

    def edit(GwRpts gwRptsInstance) {
        respond gwRptsInstance
    }

    @Transactional
    def update(GwRpts gwRptsInstance) {
        if (gwRptsInstance == null) {
            notFound()
            return
        }

        if (gwRptsInstance.hasErrors()) {
            respond gwRptsInstance.errors, view:'edit'
            return
        }

        gwRptsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GwRpts.label', default: 'GwRpts'), gwRptsInstance.id])
                redirect gwRptsInstance
            }
            '*'{ respond gwRptsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GwRpts gwRptsInstance) {

        if (gwRptsInstance == null) {
            notFound()
            return
        }

        gwRptsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GwRpts.label', default: 'GwRpts'), gwRptsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gwRpts.label', default: 'GwRpts'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
