<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/resources/js/jquery/jquery-2.1.3.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dropbox</title>
</head>
<body>
<form method="post">
    <table class="table">
        <tr>
            <th><h3>Login</h3></th>
            <c:choose>
            <c:when test="${empty failedLogin}">
        <tr>
            <td><input class="input" type="text" name="login" value='' maxlength="30" required></td>
        </tr>
        </tr>
        <tr>
            <th><h3>Password</h3></th>
        <tr>
            <td><input type="password" name="password" maxlength="30" required></td>
        </tr>
            </c:when>
            <c:otherwise>
            <td><input type="text" name="login" value="${failedLogin}" maxlength="30" required></td>
        </tr>
        <tr>
            <td><input type="password" name="password" maxlength="30" required></td>
        </tr>
        <tr>
            <td><span style="color: red">wrong login or password</span></td>
            </c:otherwise>
            </c:choose>
        </tr>
    </table>
    <input type="submit" value="Submit">
</form>
<button onclick="window.location.href ='/registration'">Register</button>
</body>
</html>
