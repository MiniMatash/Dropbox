<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@ include file="/WEB-INF/css/style.css"%>
</style>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main page</title>
</head>
<body>
<div id="header">
    <a href="./mainPage">
        <div id="homeButton"></div>
    </a>
    <div id="headerBody"><h2>Dropbox</h2></div>
</div>
<div id="fileOption"></div>
<div id="filesBody"></div>
</body>

<script type="text/javascript" src="/resources/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-form.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-validator.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-validator-methods.js"></script>
<script type="text/javascript" src="/resources/js/display.js"></script>
<script type="text/javascript" src="/resources/js/getFileTree.js"></script>
</html>
