package edu.tamu.banner.eprintreport

import java.sql.Blob
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class GwRptsGtt implements Serializable {

	static mapWith = "none"

	BigDecimal gwRptsGttSequence
	String gwRptsGttObjectName
	String gwRptsGttSid
	String gwRptsGttMime
	Date gwRptsGttCreateDate
	Integer gwRptsGttTimesAccessed
	Date gwRptsGttLastAccessed
	String gwRptsGttJobParameters
	Blob gwRptsGttBlob

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append gwRptsGttSequence
		builder.append gwRptsGttObjectName
		builder.append gwRptsGttSid
		builder.append gwRptsGttMime
		builder.append gwRptsGttCreateDate
		builder.append gwRptsGttTimesAccessed
		builder.append gwRptsGttLastAccessed
		builder.append gwRptsGttJobParameters
		builder.append gwRptsGttBlob
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append gwRptsGttSequence, other.gwRptsGttSequence
		builder.append gwRptsGttObjectName, other.gwRptsGttObjectName
		builder.append gwRptsGttSid, other.gwRptsGttSid
		builder.append gwRptsGttMime, other.gwRptsGttMime
		builder.append gwRptsGttCreateDate, other.gwRptsGttCreateDate
		builder.append gwRptsGttTimesAccessed, other.gwRptsGttTimesAccessed
		builder.append gwRptsGttLastAccessed, other.gwRptsGttLastAccessed
		builder.append gwRptsGttJobParameters, other.gwRptsGttJobParameters
		builder.append gwRptsGttBlob, other.gwRptsGttBlob
		builder.isEquals()
	}

	static mapping = {
		id composite: ["gwRptsGttSequence", "gwRptsGttObjectName", "gwRptsGttSid", "gwRptsGttMime", "gwRptsGttCreateDate", "gwRptsGttTimesAccessed", "gwRptsGttLastAccessed", "gwRptsGttJobParameters", "gwRptsGttBlob"]
		version false
	}

	static constraints = {
		gwRptsGttObjectName maxSize: 480
		gwRptsGttSid nullable: true, maxSize: 32
		gwRptsGttMime nullable: true, maxSize: 16
		gwRptsGttTimesAccessed nullable: true
		gwRptsGttLastAccessed nullable: true
		gwRptsGttJobParameters nullable: true, maxSize: 800
		gwRptsGttBlob nullable: true
	}
}
