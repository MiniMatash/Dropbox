<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dropbox</title>

</head>
<body>
<form method="post">
    <table class="table">
        <tr>
            <th><h3>Login</h3></th>
            <th><h3>Password</h3></th>
        </tr>
        <tr>
            <c:choose>
            <c:when test="${empty failedLogin}">
                <td><input class="input" type="text" name="login" value='' maxlength="30" required></td>
                <td><input type="password" name="password" maxlength="30" required></td>
            </c:when>
            <c:otherwise>
            <td><input type="text" name="login" value="${failedLogin}" maxlength="30" required></td>
            <td><input type="password" name="password" maxlength="30" required></td>
        </tr>
        <tr><td><span style="color: red">wrong login or password</span></td>
        </c:otherwise>
        </c:choose>
        </tr>
    </table>
    <input type="submit" value="Submit">
</form>
<button onclick="window.location.href ='/registration'">Register</button>
</body>
</html>
