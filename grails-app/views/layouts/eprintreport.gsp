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
    <title><g:layoutTitle default="Grails"/></title>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'styles.css')}" type="text/css">--}%
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'eprintreport.css')}" type="text/css">
    <g:layoutHead/>
</head>
<body>
<div class="container">
    <g:layoutBody/>
</div>


</body>
</html>