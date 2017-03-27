import groovy.sql.Sql

class BootStrap {

    def dataSource

    def init = { servletContext ->
        String gwrptsFilePath = servletContext.getRealPath("erpts_gw_rpts_2_h2_half_rows.sql")
        String gwrptsdefFilePath = servletContext.getRealPath("erpts_gw_rpts_def_2_h2.sql")
        String gwrptsSql = new File(gwrptsFilePath).text
        Sql sql = new Sql(dataSource: dataSource)
        sql.execute(gwrptsSql)
        String gwrptsdefSql = new File(gwrptsdefFilePath).text
        sql.execute(gwrptsdefSql)
        def rows = sql.rows("select * from GW_RPTS_DEF WHERE GW_RPTS_DEF_OBJECT_NAME = 'PWS_HISTORY_HONORS'")
        assert rows.size() == 1
        println rows.join('\n')
    }
    def destroy = {
    }
}
