<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/7/2023
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Login</title>
</head>

<body>
    <form action="/Baloot_ShoppingCenter_Phase2_war_exploded/login_validation" method="get">
        <label>Username:</label>
        <input name="username" type="text" />
        <br>
        <label>Password:</label>
        <input name="password" type="text" />
        <br>
        <button type="submit">Login</button>
    </form>
</body>
</html>