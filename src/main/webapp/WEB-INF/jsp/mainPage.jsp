<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@ include file="/WEB-INF/css/jquery-ui.css"%>
    <%@ include file="/WEB-INF/css/style.css"%>
</style>
<html>
<head>
    <link rel="shortcut icon" href=" ">
    <script type="text/javascript" src="/resources/js/jquery/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/jquery-form.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/jquery-validator.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/jquery-validator-methods.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/display.js"></script>
    <script type="text/javascript" src="/resources/js/getFileTree.js"></script>
    <script type="text/javascript" src="/resources/js/getFileTreeOptions.js"></script>
    <script type="text/javascript" src="/resources/js/logout.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main page</title>
</head>
<body>
<div id="header">
    <a href="./home">
        <div id="homeButton"></div>
    </a>
    <div id="headerBody"><h2>Dropbox</h2></div>
    <button id="logout" onclick="logout()">Logout</button>
</div>
<div id="fileOptions"></div>
<div id="fileTreeOptions" style="height: 50px"></div>
<div id="filesBody"></div>
</body>
</html>
