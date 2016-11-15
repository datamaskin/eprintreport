
<%@ page import="edu.tamu.banner.eprintreport.GwRptsDef" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gwRptsDef.label', default: 'GwRptsDef')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gwRptsDef" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gwRptsDef" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gwRptsDef">
			
				<g:if test="${gwRptsDefInstance?.gwRptsDefObjectName}">
				<li class="fieldcontain">
					<span id="gwRptsDefObjectName-label" class="property-label"><g:message code="gwRptsDef.gwRptsDefObjectName.label" default="Report Name" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsDefObjectName-label"><g:fieldValue bean="${gwRptsDefInstance}" field="gwRptsDefObjectName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsDefInstance?.gwRptsDefObjectDesc}">
				<li class="fieldcontain">
					<span id="gwRptsDefObjectDesc-label" class="property-label"><g:message code="gwRptsDef.gwRptsDefObjectDesc.label" default="Gw Rpts Def Object Desc" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsDefObjectDesc-label"><g:fieldValue bean="${gwRptsDefInstance}" field="gwRptsDefObjectDesc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsDefInstance?.gwRptsDefMaintainedDept}">
				<li class="fieldcontain">
					<span id="gwRptsDefMaintainedDept-label" class="property-label"><g:message code="gwRptsDef.gwRptsDefMaintainedDept.label" default="Gw Rpts Def Maintained Dept" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsDefMaintainedDept-label"><g:fieldValue bean="${gwRptsDefInstance}" field="gwRptsDefMaintainedDept"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsDefInstance?.gwRptsDefMaintainedColl}">
				<li class="fieldcontain">
					<span id="gwRptsDefMaintainedColl-label" class="property-label"><g:message code="gwRptsDef.gwRptsDefMaintainedColl.label" default="Gw Rpts Def Maintained Coll" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsDefMaintainedColl-label"><g:fieldValue bean="${gwRptsDefInstance}" field="gwRptsDefMaintainedColl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsDefInstance?.gwRptsDefRetentionDays}">
				<li class="fieldcontain">
					<span id="gwRptsDefRetentionDays-label" class="property-label"><g:message code="gwRptsDef.gwRptsDefRetentionDays.label" default="Gw Rpts Def Retention Days" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsDefRetentionDays-label"><g:fieldValue bean="${gwRptsDefInstance}" field="gwRptsDefRetentionDays"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsDefInstance?.gwRptsDefUserid}">
				<li class="fieldcontain">
					<span id="gwRptsDefUserid-label" class="property-label"><g:message code="gwRptsDef.gwRptsDefUserid.label" default="Gw Rpts Def Userid" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsDefUserid-label"><g:fieldValue bean="${gwRptsDefInstance}" field="gwRptsDefUserid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gwRptsDefInstance?.gwRptsDefActivityDate}">
				<li class="fieldcontain">
					<span id="gwRptsDefActivityDate-label" class="property-label"><g:message code="gwRptsDef.gwRptsDefActivityDate.label" default="Gw Rpts Def Activity Date" /></span>
					
						<span class="property-value" aria-labelledby="gwRptsDefActivityDate-label"><g:formatDate date="${gwRptsDefInstance?.gwRptsDefActivityDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:gwRptsDefInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${gwRptsDefInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
