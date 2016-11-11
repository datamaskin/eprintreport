package edu.tamu.banner.eprintreport

import grails.transaction.Transactional
import groovy.sql.Sql
import org.apache.log4j.Logger

@Transactional
class CompassReportsService {

    def dataSource
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
}