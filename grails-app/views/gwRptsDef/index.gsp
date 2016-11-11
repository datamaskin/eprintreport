
<%@ page import="edu.tamu.banner.eprintreport.GwRptsDef" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="eprintreport">
		<g:set var="entityName" value="${message(code: 'gwRptsDef.label', default: 'GwRptsDef')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="header">
			<div id="logo">
				<asset:image src="bannereprint.gif" />
			</div>
		</div>
		<a href="#list-gwRptsDef" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gwRptsDef" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="reporttable" >
			<thead>
					<tr>
					
						<g:sortableColumn property="gwRptsDefObjectName" title="${message(code: 'gwRptsDef.gwRptsDefObjectName.label', default: 'Gw Rpts Def Object Name')}" />
					
						<g:sortableColumn property="gwRptsDefObjectDesc" title="${message(code: 'gwRptsDef.gwRptsDefObjectDesc.label', default: 'Gw Rpts Def Object Desc')}" />
					
						<g:sortableColumn property="gwRptsDefMaintainedDept" title="${message(code: 'gwRptsDef.gwRptsDefMaintainedDept.label', default: 'Gw Rpts Def Maintained Dept')}" />
					
						<g:sortableColumn property="gwRptsDefMaintainedColl" title="${message(code: 'gwRptsDef.gwRptsDefMaintainedColl.label', default: 'Gw Rpts Def Maintained Coll')}" />
					
						<g:sortableColumn property="gwRptsDefRetentionDays" title="${message(code: 'gwRptsDef.gwRptsDefRetentionDays.label', default: 'Gw Rpts Def Retention Days')}" />
					
						<g:sortableColumn property="gwRptsDefUserid" title="${message(code: 'gwRptsDef.gwRptsDefUserid.label', default: 'Gw Rpts Def Userid')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gwRptsDefInstanceList}" status="i" var="gwRptsDefInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gwRptsDefInstance.id}">${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefObjectName")}</g:link></td>
					
						<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefObjectDesc")}</td>
					
						<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefMaintainedDept")}</td>
					
						<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefMaintainedColl")}</td>
					
						<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefRetentionDays")}</td>
					
						<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefUserid")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gwRptsDefInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
