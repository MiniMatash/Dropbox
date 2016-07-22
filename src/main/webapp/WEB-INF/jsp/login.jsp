<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dropbox</title>

</head>
<body>
<form method="post">
    <table class="table">
        <tr><th><h3>Login</h3></th>
            <th><h3>Password</h3></th>
        </tr>
        <tr><td><input type="text" name="login" maxlength="30" required></td>
            <td><input type="password" name="password" maxlength="30" required></td>
        </tr>
    </table>
    <input type="submit" value="Submit">
</form>
<button onclick="window.location.href ='/registration'">Register</button>
</body>
</html>
