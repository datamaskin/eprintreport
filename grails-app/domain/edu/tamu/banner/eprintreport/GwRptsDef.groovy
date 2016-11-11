package edu.tamu.banner.eprintreport

class GwRptsDef {

	String gwRptsDefObjectName
	String gwRptsDefObjectDesc
	String gwRptsDefMaintainedDept
	String gwRptsDefMaintainedColl
	Integer gwRptsDefRetentionDays
	String gwRptsDefUserid
	Date gwRptsDefActivityDate

	static mapping = {
		id name: "gwRptsDefObjectName", generator: "assigned"
		version false
	}

	static constraints = {
		gwRptsDefObjectName maxSize: 480
		gwRptsDefObjectDesc nullable: true, maxSize: 1200
		gwRptsDefMaintainedDept nullable: true, maxSize: 120
		gwRptsDefMaintainedColl nullable: true, maxSize: 8
		gwRptsDefRetentionDays nullable: true
		gwRptsDefUserid nullable: true, maxSize: 120
		gwRptsDefActivityDate nullable: true
	}
}
