package com.example.baloot_shoppingcenter_phase2.servlets;

import com.example.baloot_shoppingcenter_phase2.Shopping_WebSite;
import com.example.baloot_shoppingcenter_phase2.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Add_Credits_Validation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logged_in_user_name = Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name();
        if(logged_in_user_name != null){
            int amount_of_credit = Integer.parseInt(request.getParameter("credit"));
            if(amount_of_credit >= 0) {
                User logged_in_user = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(logged_in_user_name);
                logged_in_user.increase_credit(amount_of_credit);
                request.setAttribute("Success_Massage", "Your Credit is now: " + logged_in_user.get_credit());
                RequestDispatcher rd = request.getRequestDispatcher("200_Success.jsp");
                rd.forward(request, response);
            }
        }
        request.setAttribute("Error_Massage", "Invalid Credit");
        RequestDispatcher rd = request.getRequestDispatcher("400_Error.jsp");
        rd.forward(request, response);
    }
}