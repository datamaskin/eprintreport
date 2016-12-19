<%@ page import="edu.tamu.banner.eprintreport.GwRpts" %>



<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsObjectName', 'error')} required">
	<label for="gwRptsObjectName">
		<g:message code="gwRpts.gwRptsObjectName.label" default="Gw Rpts Object Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="gwRptsObjectName" cols="40" rows="5" maxlength="480" required="" value="${gwRptsInstance?.gwRptsObjectName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsSid', 'error')} ">
	<label for="gwRptsSid">
		<g:message code="gwRpts.gwRptsSid.label" default="Gw Rpts Sid" />
		
	</label>
	<g:textField name="gwRptsSid" maxlength="32" value="${gwRptsInstance?.gwRptsSid}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsMime', 'error')} ">
	<label for="gwRptsMime">
		<g:message code="gwRpts.gwRptsMime.label" default="Gw Rpts Mime" />
		
	</label>
	<g:textField name="gwRptsMime" maxlength="16" value="${gwRptsInstance?.gwRptsMime}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsTimesAccessed', 'error')} ">
	<label for="gwRptsTimesAccessed">
		<g:message code="gwRpts.gwRptsTimesAccessed.label" default="Gw Rpts Times Accessed" />
		
	</label>
	<g:field name="gwRptsTimesAccessed" type="number" value="${gwRptsInstance.gwRptsTimesAccessed}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsLastAccessed', 'error')} ">
	<label for="gwRptsLastAccessed">
		<g:message code="gwRpts.gwRptsLastAccessed.label" default="Gw Rpts Last Accessed" />
		
	</label>
	<g:datePicker name="gwRptsLastAccessed" precision="day"  value="${gwRptsInstance?.gwRptsLastAccessed}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsJobParameters', 'error')} ">
	<label for="gwRptsJobParameters">
		<g:message code="gwRpts.gwRptsJobParameters.label" default="Gw Rpts Job Parameters" />
		
	</label>
	<g:textArea name="gwRptsJobParameters" cols="40" rows="5" maxlength="800" value="${gwRptsInstance?.gwRptsJobParameters}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsBlob', 'error')} ">
	<label for="gwRptsBlob">
		<g:message code="gwRpts.gwRptsBlob.label" default="Gw Rpts Blob" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsCreateDate', 'error')} required">
	<label for="gwRptsCreateDate">
		<g:message code="gwRpts.gwRptsCreateDate.label" default="Gw Rpts Create Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="gwRptsCreateDate" precision="day"  value="${gwRptsInstance?.gwRptsCreateDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: gwRptsInstance, field: 'gwRptsSequence', 'error')} required">
	<label for="gwRptsSequence">
		<g:message code="gwRpts.gwRptsSequence.label" default="Gw Rpts Sequence" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="gwRptsSequence" value="${fieldValue(bean: gwRptsInstance, field: 'gwRptsSequence')}" required=""/>

</div>

