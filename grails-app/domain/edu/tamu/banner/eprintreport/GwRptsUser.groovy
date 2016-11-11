package edu.tamu.banner.eprintreport

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class GwRptsUser implements Serializable {

	String gwRptsUserUsername
	String gwRptsUserObjectAccessed
	Integer gwRptsUserPidm
	Integer gwRptsUserTimesAccessed
	String gwRptsUserIp
	Date gwRptsUserActivityDate

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append gwRptsUserUsername
		builder.append gwRptsUserObjectAccessed
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append gwRptsUserUsername, other.gwRptsUserUsername
		builder.append gwRptsUserObjectAccessed, other.gwRptsUserObjectAccessed
		builder.isEquals()
	}

	static mapping = {
		id composite: ["gwRptsUserUsername", "gwRptsUserObjectAccessed"]
		version false
	}

	static constraints = {
		gwRptsUserUsername maxSize: 30
		gwRptsUserObjectAccessed maxSize: 120
		gwRptsUserPidm nullable: true
		gwRptsUserTimesAccessed nullable: true
		gwRptsUserIp nullable: true, maxSize: 45
		gwRptsUserActivityDate nullable: true
	}
}
