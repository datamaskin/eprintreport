
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

		%{--<div id="downloader_application" class="">
			<h3>Please select your documents</h3>
			<form action="#" id="download_form">
				<label>
					--}%%{--<input type="checkbox" data-url="{{site.baseurl}}/web-app/WEB-INF/files/599.txt.zip" />--}%%{--
					<input type="checkbox" data-url="http://localhost:8080/EprintReport/#" />
					599.txt.zip
				</label>
				<button type="submit" class="btn btn-primary">Download</button>
			</form>

			<div class="progress hide" id="progress_bar">
				<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
				</div>
			</div>

			<p class="hide" id="result"></p>

		</div>--}%

		</body>

</html>
