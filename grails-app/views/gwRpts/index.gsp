
<%@ page import="edu.tamu.banner.eprintreport.GwRpts" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gwRpts.label', default: 'GwRpts')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gwRpts" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gwRpts" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="gwRptsObjectName" title="${message(code: 'gwRpts.gwRptsObjectName.label', default: 'Gw Rpts Object Name')}" />
					
						<g:sortableColumn property="gwRptsSid" title="${message(code: 'gwRpts.gwRptsSid.label', default: 'Gw Rpts Sid')}" />
					
						<g:sortableColumn property="gwRptsMime" title="${message(code: 'gwRpts.gwRptsMime.label', default: 'Gw Rpts Mime')}" />
					
						<g:sortableColumn property="gwRptsTimesAccessed" title="${message(code: 'gwRpts.gwRptsTimesAccessed.label', default: 'Gw Rpts Times Accessed')}" />
					
						<g:sortableColumn property="gwRptsLastAccessed" title="${message(code: 'gwRpts.gwRptsLastAccessed.label', default: 'Gw Rpts Last Accessed')}" />
					
						<g:sortableColumn property="gwRptsJobParameters" title="${message(code: 'gwRpts.gwRptsJobParameters.label', default: 'Gw Rpts Job Parameters')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gwRptsInstanceList}" status="i" var="gwRptsInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gwRptsInstance.id}">${fieldValue(bean: gwRptsInstance, field: "gwRptsObjectName")}</g:link></td>
					
						<td>${fieldValue(bean: gwRptsInstance, field: "gwRptsSid")}</td>
					
						<td>${fieldValue(bean: gwRptsInstance, field: "gwRptsMime")}</td>
					
						<td>${fieldValue(bean: gwRptsInstance, field: "gwRptsTimesAccessed")}</td>
					
						<td><g:formatDate date="${gwRptsInstance.gwRptsLastAccessed}" /></td>
					
						<td>${fieldValue(bean: gwRptsInstance, field: "gwRptsJobParameters")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gwRptsInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
