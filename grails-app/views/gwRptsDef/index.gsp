
<%@ page import="edu.tamu.eis.GwRptsDef" %>
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
						%{--User login name goes here--}%
					</div>
				</div>
			</div>
			<div id="schoollogo">
				<asset:image src="tamulogo.png" />
			</div>
		</div>
		<a href="#list-gwRptsDef" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<dt:datatable name="EprintTable" serverDataLoad="true" controller="GwRptsDef" dataAction="gwrptsReportDefJSON_H2" order="[[1,'asc']]">
            <dt:column name="gwRptsDefObjectName" defaultContent="" heading=" " dataFunction="${{domainClass -> ''}}" orderable="false" className="details-control" />
			<dt:column name="gwRptsDefObjectName" headingKey="gwRptsDef.gwRptsDefObjectName.label"/>
            <dt:column name="gwRptsDefObjectDesc" headingKey="gwRptsDef.gwRptsDefObjectDesc.label"/>
			<dt:column name="gwRptsDefRetentionDays" headingKey="gwRptsDef.gwRptsDefRetentionDays.label"/>
            <dt:column name="gwRptsDefActivityDate" headingKey="gwRptsDef.gwRptsDefActivityDate.label"/>
		</dt:datatable>
        <asset:deferredScripts/>

		</body>

</html>
