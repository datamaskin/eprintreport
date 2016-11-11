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
		gwRptsDefObjectName maxSize: 120
		gwRptsDefObjectDesc nullable: true, maxSize: 300
		gwRptsDefMaintainedDept nullable: true, maxSize: 30
		gwRptsDefMaintainedColl nullable: true, maxSize: 2
		gwRptsDefRetentionDays nullable: true
		gwRptsDefUserid nullable: true, maxSize: 30
		gwRptsDefActivityDate nullable: true
	}
}
