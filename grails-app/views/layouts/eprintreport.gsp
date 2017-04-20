<%--
  Created by IntelliJ IDEA.
  User: datamaskinaggie
  Date: 11/10/16
  Time: 4:22 PM
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="ePrint"/></title>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="jquery-ui-1.12.1.custom"/>
    <asset:stylesheet src="eprintreport.css" />
    <link rel="stylesheet" href="/EprintReport/css/grails-datatables.css">
    <link rel="stylesheet" href="/EprintReport/css/jquery-ui/jquery-ui.css">
    <link rel="stylesheet" href="/EprintReport/css/dataTables.jqueryui.css">
    %{--<link rel="stylesheet" href="/EprintReport/css/jquery.qtip.css">--}%
    <g:javascript library="jquery" plugin="jquery"/>
    <script type="text/javascript" src="/EprintReport/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="/EprintReport/js/dataTables.fullNumbersCallback.js"></script>
    <script type="text/javascript" src="/EprintReport/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/EprintReport/js/dataTables.jqueryui.js"></script>
    %{--<script type="text/javascript" src="/EprintReport/js/jquery.qtip.js"></script>--}%
    <g:layoutHead/>
</head>
<body>
<div class="container">
    <g:layoutBody/>
</div>


</body>
</html>