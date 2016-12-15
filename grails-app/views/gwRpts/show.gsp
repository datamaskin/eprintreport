
<%@ page import="edu.tamu.banner.eprintreport.GwRpts" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gwRpts.label', default: 'GwRpts')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gwRpts" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gwRpts" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gwRpts">
			
				<g:if test="${gwRptsInstance?.gwRptsObjectName}">
				<li class="fieldcontain">
					<span id="gwRptsObjectName-label" class="property-label"><g:message code="gwRpts.gwRptsObjectName.label" default="Gw Rpts Object Name" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsObjectName-label"><g:fieldValue bean="${gwRptsInstance}" field="gwRptsObjectName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsInstance?.gwRptsSid}">
				<li class="fieldcontain">
					<span id="gwRptsSid-label" class="property-label"><g:message code="gwRpts.gwRptsSid.label" default="Gw Rpts Sid" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsSid-label"><g:fieldValue bean="${gwRptsInstance}" field="gwRptsSid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsInstance?.gwRptsMime}">
				<li class="fieldcontain">
					<span id="gwRptsMime-label" class="property-label"><g:message code="gwRpts.gwRptsMime.label" default="Gw Rpts Mime" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsMime-label"><g:fieldValue bean="${gwRptsInstance}" field="gwRptsMime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsInstance?.gwRptsTimesAccessed}">
				<li class="fieldcontain">
					<span id="gwRptsTimesAccessed-label" class="property-label"><g:message code="gwRpts.gwRptsTimesAccessed.label" default="Gw Rpts Times Accessed" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsTimesAccessed-label"><g:fieldValue bean="${gwRptsInstance}" field="gwRptsTimesAccessed"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsInstance?.gwRptsLastAccessed}">
				<li class="fieldcontain">
					<span id="gwRptsLastAccessed-label" class="property-label"><g:message code="gwRpts.gwRptsLastAccessed.label" default="Gw Rpts Last Accessed" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsLastAccessed-label"><g:formatDate date="${gwRptsInstance?.gwRptsLastAccessed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsInstance?.gwRptsJobParameters}">
				<li class="fieldcontain">
					<span id="gwRptsJobParameters-label" class="property-label"><g:message code="gwRpts.gwRptsJobParameters.label" default="Gw Rpts Job Parameters" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsJobParameters-label"><g:fieldValue bean="${gwRptsInstance}" field="gwRptsJobParameters"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsInstance?.gwRptsBlob}">
				<li class="fieldcontain">
					<span id="gwRptsBlob-label" class="property-label"><g:message code="gwRpts.gwRptsBlob.label" default="Gw Rpts Blob" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsBlob-label"><g:fieldValue bean="${gwRptsInstance}" field="gwRptsBlob"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsInstance?.gwRptsCreateDate}">
				<li class="fieldcontain">
					<span id="gwRptsCreateDate-label" class="property-label"><g:message code="gwRpts.gwRptsCreateDate.label" default="Gw Rpts Create Date" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsCreateDate-label"><g:formatDate date="${gwRptsInstance?.gwRptsCreateDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsInstance?.gwRptsSequence}">
				<li class="fieldcontain">
					<span id="gwRptsSequence-label" class="property-label"><g:message code="gwRpts.gwRptsSequence.label" default="Gw Rpts Sequence" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsSequence-label"><g:fieldValue bean="${gwRptsInstance}" field="gwRptsSequence"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:gwRptsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${gwRptsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
