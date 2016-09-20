<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@ include file="/WEB-INF/css/style.css"%>
    <%@ include file="/WEB-INF/css/jquery-ui.css"%>
</style>
<html>
<head>
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
    <button id="logout" style="position:absolute;left: 90%;" onclick="logout()">Logout</button>
</div>
<div id="fileTreeOptions"></div>
<div id="fileOptions"></div>
<div id="filesBody"></div>
</body>

<script type="text/javascript" src="/resources/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-form.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-validator.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-validator-methods.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/js/display.js"></script>
<script type="text/javascript" src="/resources/js/getFileTree.js"></script>
<script type="text/javascript" src="/resources/js/getFileTreeOptions.js"></script>
<script type="text/javascript" src="/resources/js/logout.js"></script>
<script type="text/javascript" src="/resources/js/createFolder.js"></script>
</html>
