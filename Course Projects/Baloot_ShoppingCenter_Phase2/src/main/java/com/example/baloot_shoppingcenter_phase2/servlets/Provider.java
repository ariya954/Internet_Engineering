package com.example.baloot_shoppingcenter_phase2.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Provider extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String RequestURL = request.getRequestURL().toString();
        int provider_id = Integer.parseInt(RequestURL.substring(RequestURL.indexOf("provider/") + 12, RequestURL.length() - 3));
        request.setAttribute("provider_id", provider_id);
        RequestDispatcher rd = request.getRequestDispatcher("/provider.jsp");
        rd.forward(request, response);
    }
}