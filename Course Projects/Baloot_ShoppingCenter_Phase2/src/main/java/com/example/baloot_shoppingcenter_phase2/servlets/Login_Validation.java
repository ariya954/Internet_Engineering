package com.example.baloot_shoppingcenter_phase2.servlets;

import com.example.baloot_shoppingcenter_phase2.Shopping_WebSite;
import com.example.baloot_shoppingcenter_phase2.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login_Validation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input_username = request.getParameter("username");
        String input_password = request.getParameter("password");
        User logged_in_user = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(input_username);
        if(logged_in_user != null){
            if(logged_in_user.get_password().equals(input_password)) {
                Shopping_WebSite.get_Shopping_WebSite_instance().change_current_logged_in_user_name(input_username);
                RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                rd.forward(request, response);
            }
        }
        request.setAttribute("Error_Massage", "Invalid Username or Password");
        RequestDispatcher rd = request.getRequestDispatcher("400_Error.jsp");
        rd.forward(request, response);
    }
}