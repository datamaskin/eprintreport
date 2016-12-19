package edu.tamu.banner.eprintreport

import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GwRptsDefController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond GwRptsDef.list(params), model:[gwRptsDefInstanceCount: GwRptsDef.count()]
        println GwRptsDef.list(params) as JSON
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
