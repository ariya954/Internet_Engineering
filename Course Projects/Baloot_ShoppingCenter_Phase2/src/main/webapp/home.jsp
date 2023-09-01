<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/7/2023
  Time: 7:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.baloot_shoppingcenter_phase2.Shopping_WebSite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
</head>
<body>
    <ul>
        <li id="username">username: <%= Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name()%></li>
        <li>
            <a href="/Baloot_ShoppingCenter_Phase2_war_exploded/commodities">Commodities</a>
        </li>
        <li>
            <a href="/Baloot_ShoppingCenter_Phase2_war_exploded/user">Buy List</a>
        </li>
        <li>
            <a href="/Baloot_ShoppingCenter_Phase2_war_exploded/credit">Add Credit</a>
        </li>
        <li>
            <a href="/Baloot_ShoppingCenter_Phase2_war_exploded/logout">Log Out</a>
        </li>
    </ul>
</body>
</html>