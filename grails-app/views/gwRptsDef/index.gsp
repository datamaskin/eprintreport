
<%@ page import="edu.tamu.banner.eprintreport.GwRptsDef" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="eprintreport">
		<g:set var="entityName" value="${message(code: 'gwRptsDef.label', default: 'GwRptsDef')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<style>
		</style>
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

		</body>

</html>
