<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/8/2023
  Time: 6:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.baloot_shoppingcenter_phase2.Shopping_WebSite" %>
<%@ page import="com.example.baloot_shoppingcenter_phase2.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
    <meta charset="UTF-8">
    <title>BuyList</title>
    <style>
        li {
            padding: 5px
        }
        table{
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<ul>
    <% String current_logged_in_user_name = Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name();
       User current_logged_in_user = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(current_logged_in_user_name); %>
    <li id="username">Username: <%= current_logged_in_user.get_username()%></li>
    <li id="email">Email: <%= current_logged_in_user.get_email()%></li>
    <li id="birthDate">Birth Date: <%= current_logged_in_user.get_birthDate()%></li>
    <li id="address">Address: <%= current_logged_in_user.get_address()%></li>
    <li id="credit">Credit: <%= current_logged_in_user.get_credit()%></li>
    <% if(request.getAttribute("discount") != null) {
        int discount = Integer.parseInt(request.getAttribute("discount").toString());%>
        <li>Current Buy List Price: <%= (Shopping_WebSite.get_Shopping_WebSite_instance().get_total_buy_list_price(current_logged_in_user_name) * (100 - discount))/100%></li>
    <% }else {%>
        <li>Current Buy List Price: <%= Shopping_WebSite.get_Shopping_WebSite_instance().get_total_buy_list_price(current_logged_in_user_name)%></li>
    <% } %>
    <li>
        <a href="/Baloot_ShoppingCenter_Phase2_war_exploded/credit">Add Credit</a>
    </li>
    <li>
        <form action="/Baloot_ShoppingCenter_Phase2_war_exploded/buyList" method="GET">
            <label>Submit & Pay</label>
            <input id="payment" type="hidden" name="action" value="payment">
            <% if(request.getAttribute("discount") != null) { %>
                <input id="discount_code" type="hidden" name="discountCode" value="<%= request.getAttribute("discountCode").toString()%>">
                <input id="discount" type="hidden" name="discount" value="<%= request.getAttribute("discount").toString()%>">
            <% }else {%>
                <input id="discount_code" type="hidden" name="discountCode" value="">
                <input id="discount" type="hidden" name="discount" value="0">
            <% } %>
            <button type="submit">Payment</button>
        </form>
    </li>
    <li>
        <form action="/Baloot_ShoppingCenter_Phase2_war_exploded/discount_validation" method="GET">
            <label>Discount Code:</label>
            <input type="text" name="discount_code" value="">
            <button type="submit" name="submit_discount" value="clear">Submit</button>
        </form>
    </li>
</ul>
<%= Shopping_WebSite.get_Shopping_WebSite_instance().show_user_buy_list_page_handler(current_logged_in_user)%>