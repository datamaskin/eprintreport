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
		gwRptsObjectName maxSize: 480
		gwRptsSid nullable: true, maxSize: 32
		gwRptsMime nullable: true, maxSize: 16
		gwRptsTimesAccessed nullable: true
		gwRptsLastAccessed nullable: true
		gwRptsJobParameters nullable: true, maxSize: 800
		gwRptsBlob nullable: true
	}
}
