package com.example.baloot_shoppingcenter_phase2.servlets;

import com.example.baloot_shoppingcenter_phase2.Shopping_WebSite;
import com.example.baloot_shoppingcenter_phase2.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Commodity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String current_logged_in_user_name = Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name();
        String action = request.getParameter("action");
        if(action.equals("like")){
            int comment_id = Integer.parseInt(request.getParameter("comment_id"));
            Shopping_WebSite.get_Shopping_WebSite_instance().rate_comment(current_logged_in_user_name, comment_id, 1);
            RequestDispatcher rd = request.getRequestDispatcher("/commodities/%7B" + request.getParameter("commodity_id") + "%7D");
            rd.forward(request, response);
        }
        if(action.equals("dislike")){
            int comment_id = Integer.parseInt(request.getParameter("comment_id"));
            Shopping_WebSite.get_Shopping_WebSite_instance().rate_comment(current_logged_in_user_name, comment_id, -1);
            RequestDispatcher rd = request.getRequestDispatcher("/commodities/%7B" + request.getParameter("commodity_id") + "%7D");
            rd.forward(request, response);
        }
        if(action.equals("comment")){
            String comment = request.getParameter("comment");
            String comment_date = request.getParameter("comment_date");
            int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));
            User current_logged_in_user = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(current_logged_in_user_name);
            Shopping_WebSite.get_Shopping_WebSite_instance().add_user_comment(comment, current_logged_in_user.get_email(), commodity_id, comment_date);
            request.setAttribute("Success_Massage", "Your comment to this commodity is now: " + comment);
            RequestDispatcher rd = request.getRequestDispatcher("200_Success.jsp");
            rd.forward(request, response);
        }
        if(action.equals("rate")){
            int rate = Integer.parseInt(request.getParameter("rate"));
            int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));
            Shopping_WebSite.get_Shopping_WebSite_instance().rate_commodity(current_logged_in_user_name, commodity_id, rate);
            request.setAttribute("Success_Massage", "Your rate to this commodity is now: " + rate);
            RequestDispatcher rd = request.getRequestDispatcher("200_Success.jsp");
            rd.forward(request, response);
        }
        if(action.equals("add")){
            int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));
            Shopping_WebSite.get_Shopping_WebSite_instance().add_to_buy_list(current_logged_in_user_name, commodity_id);
            request.setAttribute("Success_Massage", "Commodity is Added to Your Buy List");
            RequestDispatcher rd = request.getRequestDispatcher("200_Success.jsp");
            rd.forward(request, response);
        }
    }
}