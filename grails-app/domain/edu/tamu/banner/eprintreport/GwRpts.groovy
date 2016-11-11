package edu.tamu.banner.eprintreport

import java.sql.Blob

class GwRpts {

	BigDecimal gwRptsSequence
	String gwRptsObjectName
	String gwRptsSid
	String gwRptsMime
	Date gwRptsCreateDate
	Integer gwRptsTimesAccessed
	Date gwRptsLastAccessed
	String gwRptsJobParameters
	Blob gwRptsBlob

	static mapping = {
		id name: "gwRptsSequence", generator: "assigned"
		version false
	}

	static constraints = {
		gwRptsObjectName maxSize: 120
		gwRptsSid nullable: true, maxSize: 8
		gwRptsMime nullable: true, maxSize: 4
		gwRptsTimesAccessed nullable: true
		gwRptsLastAccessed nullable: true
		gwRptsJobParameters nullable: true, maxSize: 200
		gwRptsBlob nullable: true
	}
}
