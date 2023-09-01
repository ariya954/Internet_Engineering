<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/7/2023
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.baloot_shoppingcenter_phase2.Shopping_WebSite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Commodities</title>
    <style>
        table{
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
    <a href="/Baloot_ShoppingCenter_Phase2_war_exploded">Home</a>
    <p id="username">username: <%= Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name()%></p>
    <br><br>
    <form action="/Baloot_ShoppingCenter_Phase2_war_exploded/commodities" method="GET">
        <label>Search:</label>
        <input type="text" name="search" value="">
        <button type="submit" name="action" value="search_by_category">Search By Cagtegory</button>
        <button type="submit" name="action" value="search_by_name">Search By Name</button>
        <button type="submit" name="action" value="clear">Clear Search</button>
    </form>
    <br><br>
    <form action="/Baloot_ShoppingCenter_Phase2_war_exploded/commodities" method="GET">
        <label>Sort By:</label>
        <button type="submit" name="action" value="sort_by_price">Price</button>
    </form>
    <br><br>
    <% String action = request.getParameter("action");
        if(action != null) { %>
            <% if(action.equals("sort_by_price")) { %>
                <%= Shopping_WebSite.get_Shopping_WebSite_instance().show_sorted_commodities_by_price_page_handler()%>
            <% }if(action.equals("search_by_category")) { %>
                <%= Shopping_WebSite.get_Shopping_WebSite_instance().show_commodity_by_category_page_handler(request.getParameter("search"))%>
            <% }if(action.equals("search_by_name")) { %>
                <%= Shopping_WebSite.get_Shopping_WebSite_instance().show_commodity_by_name_page_handler(request.getParameter("search"))%>
            <% }if(action.equals("clear")) { %>
                <%= Shopping_WebSite.get_Shopping_WebSite_instance().show_all_commodities_page_handler()%>
            <% } %>
        <% }
        else { %>
                <%= Shopping_WebSite.get_Shopping_WebSite_instance().show_all_commodities_page_handler()%>
        <% } %>
</body>
</html>