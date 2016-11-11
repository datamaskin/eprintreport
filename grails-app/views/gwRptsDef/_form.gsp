<%@ page import="edu.tamu.banner.eprintreport.GwRptsDef" %>



<div class="fieldcontain ${hasErrors(bean: gwRptsDefInstance, field: 'gwRptsDefObjectName', 'error')} required">
	<label for="gwRptsDefObjectName">
		<g:message code="gwRptsDef.gwRptsDefObjectName.label" default="Gw Rpts Def Object Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="gwRptsDefObjectName" maxlength="120" required="" value="${gwRptsDefInstance?.gwRptsDefObjectName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsDefInstance, field: 'gwRptsDefObjectDesc', 'error')} ">
	<label for="gwRptsDefObjectDesc">
		<g:message code="gwRptsDef.gwRptsDefObjectDesc.label" default="Gw Rpts Def Object Desc" />
		
	</label>
	<g:textArea name="gwRptsDefObjectDesc" cols="40" rows="5" maxlength="300" value="${gwRptsDefInstance?.gwRptsDefObjectDesc}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsDefInstance, field: 'gwRptsDefMaintainedDept', 'error')} ">
	<label for="gwRptsDefMaintainedDept">
		<g:message code="gwRptsDef.gwRptsDefMaintainedDept.label" default="Gw Rpts Def Maintained Dept" />
		
	</label>
	<g:textField name="gwRptsDefMaintainedDept" maxlength="30" value="${gwRptsDefInstance?.gwRptsDefMaintainedDept}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsDefInstance, field: 'gwRptsDefMaintainedColl', 'error')} ">
	<label for="gwRptsDefMaintainedColl">
		<g:message code="gwRptsDef.gwRptsDefMaintainedColl.label" default="Gw Rpts Def Maintained Coll" />
		
	</label>
	<g:textField name="gwRptsDefMaintainedColl" maxlength="2" value="${gwRptsDefInstance?.gwRptsDefMaintainedColl}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsDefInstance, field: 'gwRptsDefRetentionDays', 'error')} ">
	<label for="gwRptsDefRetentionDays">
		<g:message code="gwRptsDef.gwRptsDefRetentionDays.label" default="Gw Rpts Def Retention Days" />
		
	</label>
	<g:field name="gwRptsDefRetentionDays" type="number" value="${gwRptsDefInstance.gwRptsDefRetentionDays}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsDefInstance, field: 'gwRptsDefUserid', 'error')} ">
	<label for="gwRptsDefUserid">
		<g:message code="gwRptsDef.gwRptsDefUserid.label" default="Gw Rpts Def Userid" />
		
	</label>
	<g:textField name="gwRptsDefUserid" maxlength="30" value="${gwRptsDefInstance?.gwRptsDefUserid}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsDefInstance, field: 'gwRptsDefActivityDate', 'error')} ">
	<label for="gwRptsDefActivityDate">
		<g:message code="gwRptsDef.gwRptsDefActivityDate.label" default="Gw Rpts Def Activity Date" />
		
	</label>
	<g:datePicker name="gwRptsDefActivityDate" precision="day"  value="${gwRptsDefInstance?.gwRptsDefActivityDate}" default="none" noSelection="['': '']" />

</div>

