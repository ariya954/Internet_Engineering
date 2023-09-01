package com.example.baloot_shoppingcenter_phase2.servlets;

import com.example.baloot_shoppingcenter_phase2.Shopping_WebSite;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Commodities extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name() != null) {
            String RequestURL = request.getRequestURL().toString();
            if(RequestURL.contains("commodities/")){
                String commodity_id =  RequestURL.substring(RequestURL.indexOf("commodities/") + 15, RequestURL.length() - 3);
                request.setAttribute("commodity_id", commodity_id);
                RequestDispatcher rd = request.getRequestDispatcher("/commodity.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("commodities.jsp");
            rd.forward(request, response);

        }
        RequestDispatcher rd = request.getRequestDispatcher("/login");
        rd.forward(request, response);
    }
}