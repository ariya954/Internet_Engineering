<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/7/2023
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>400 Error</title>
</head>
<body>
    <h1>400 Error</h1>
    <h2><%= request.getAttribute("Error_Massage")%></h2>
</body>
</html>