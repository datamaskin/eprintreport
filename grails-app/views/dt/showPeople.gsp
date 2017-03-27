<%--
  Created by IntelliJ IDEA.
  User: datamaskinaggie
  Date: 3/22/17
  Time: 8:51 AM
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <asset:stylesheet src="grails-datatables.css" />
    <asset:stylesheet src="grails-datatables-plain.css" />
    <asset:javascript src="jquery.js" />
    <asset:javascript src="grails-datatables.js" />
</head>
<body>

<dt:datatable  name="dtexamples" serverDataLoad="true" controller="Dt" dataAction="showPeople">
    <dt:column name="firstName" />
    <dt:column name="lastName" />
    <dt:column name="email" />
</dt:datatable>
<asset:deferredScripts/>

</body>

</html>
