
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
			<div id="pagehead">
				<div id="pageheadblock">
					<div id="pageheadblockleft">
						<h2>Select Report from Repository Student Development</h2>
					</div>
					<div id="pageheadblockright">
						<h2>Student Development Repository</h2>
						<h2>${fieldValue(bean: GwRptsDef.findByGwRptsDefUseridIsNotNull(), field: "gwRptsDefUserid")}</h2>
					</div>
				</div>
			</div>
			<div id="schoollogo">
				<asset:image src="tamulogo.png" />
			</div>
		</div>
		<a href="#list-gwRptsDef" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<dt:datatable name="EprintTable" domainClass="edu.tamu.banner.eprintreport.GwRptsDef" serverDataLoad="true" order="[[1,'asc']]">
            <dt:column name="gwRptsDefObjectName" defaultContent="" heading=" " dataFunction="${{domainClass -> ''}}" orderable="false" className="details-control" />
            <dt:column name="gwRptsDefObjectName" headingKey="gwRptsDef.gwRptsDefObjectName.label"/>
            <dt:column name="gwRptsDefObjectDesc" headingKey="gwRptsDef.gwRptsDefObjectDesc.label"/>
            <dt:column name="gwRptsDefActivityDate" headingKey="gwRptsDef.gwRptsDefActivityDate.label"/>
        </dt:datatable>
        <asset:deferredScripts/>
		%{--<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>--}%
		%{--<div id="list-gwRptsDef" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="reporttable" >
			<thead>
					<tr>
					
						<g:sortableColumn property="gwRptsDefObjectName" title="${message(code: 'gwRptsDef.gwRptsDefObjectName.label', default: 'Report name')}" params="${filterParams}" />
					
						<g:sortableColumn property="gwRptsDefObjectDesc" title="${message(code: 'gwRptsDef.gwRptsDefObjectDesc.label', default: 'Description')}" params="${filterParams}" />

						<g:sortableColumn property="gwRptsLastAccessed" title="${message(code: 'gwRptsDef.gwRptsDefActivityDate.label', default: 'Latest')}" params="${filterParams}" />
					
						--}%%{--<g:sortableColumn property="gwRptsDefMaintainedDept" title="${message(code: 'gwRptsDef.gwRptsDefMaintainedDept.label', default: 'Gw Rpts Def Maintained Dept')}" />--}%%{--
					
						--}%%{--<g:sortableColumn property="gwRptsDefMaintainedColl" title="${message(code: 'gwRptsDef.gwRptsDefMaintainedColl.label', default: 'Gw Rpts Def Maintained Coll')}" />--}%%{--
					
						--}%%{--<g:sortableColumn property="gwRptsDefRetentionDays" title="${message(code: 'gwRptsDef.gwRptsDefRetentionDays.label', default: 'Gw Rpts Def Retention Days')}" />--}%%{--
					
						--}%%{--<g:sortableColumn property="gwRptsDefUserid" title="${message(code: 'gwRptsDef.gwRptsDefUserid.label', default: 'Gw Rpts Def Userid')}" />--}%%{--
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gwRptsDefInstanceList}" status="i" var="gwRptsDefInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gwRptsDefInstance.id}">${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefObjectName")}</g:link></td>
					
						<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefObjectDesc")}</td>

						<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefActivityDate")}</td>
					
						--}%%{--<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefMaintainedDept")}</td>--}%%{--
					
						--}%%{--<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefMaintainedColl")}</td>--}%%{--
					
						--}%%{--<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefRetentionDays")}</td>--}%%{--
					
						--}%%{--<td>${fieldValue(bean: gwRptsDefInstance, field: "gwRptsDefUserid")}</td>--}%%{--
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gwRptsDefInstanceCount ?: 0}" />
			</div>
		</div>--}%
	</body>
</html>
