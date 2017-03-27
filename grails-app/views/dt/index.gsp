<%--
  Created by IntelliJ IDEA.
  User: datamaskinaggie
  Date: 3/15/17
  Time: 1:51 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <asset:javascript src="jquery.js" />
    <asset:javascript src="grails-datatables.js" />
    <asset:stylesheet src="grails-datatables.css" />
    <asset:stylesheet src="grails-datatables-plain.css" />
</head>

<body>
    <dt:datatable  name="dtDataTable" serverDataLoad="true" controller="Dt" dataAction="showPeople">
        <dt:column name="firstName" />
        <dt:column name="lastName" />
        <dt:column name="email" />
    </dt:datatable>
    <asset:deferredScripts/>
</body>
</html>