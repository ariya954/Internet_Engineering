<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/7/2023
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.baloot_shoppingcenter_phase2.Shopping_WebSite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User</title>
  <style>
    li {
      padding: 5px;
    }
    table{
      width: 100%;
      text-align: center;
    }
  </style>
</head>
<body>
<%= Shopping_WebSite.get_Shopping_WebSite_instance().show_user_page_handler(request.getAttribute("username").toString())%>