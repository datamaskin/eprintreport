package edu.tamu.eis

import java.sql.Blob

/**
 * Created by datamaskinaggie on 3/24/17.
 */
class GwRpts {
    List<BigDecimal>    gwRptsSequence
    List<String>        gwRptsObjectName
    List<String>        gwRptsMime
    List<Date>          gwRptsCreateDate
    List<Blob>          gwRptsBlob
}
