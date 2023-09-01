package com.example.baloot_shoppingcenter_phase2.servlets;

import com.example.baloot_shoppingcenter_phase2.Shopping_WebSite;
import com.example.baloot_shoppingcenter_phase2.User_Discount;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class User extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name() != null){
            String username = Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name();
            request.setAttribute("username", username);
            String action = request.getParameter("action");
            if(action != null) {
                if (action.equals("pay")) {
                    String discount_code = request.getParameter("discount_code");
                    if(!discount_code.isEmpty()){
                        User_Discount user_discount = Shopping_WebSite.get_Shopping_WebSite_instance().get_User_Discount(discount_code);
                        if(user_discount != null){
                            if(!user_discount.is_expired()) {
                                Shopping_WebSite.get_Shopping_WebSite_instance().add_to_purchased_list(username, user_discount.get_discount());
                                user_discount.expire_discount();
                                RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
                                rd.forward(request, response);
                            }
                            request.setAttribute("Error_Massage", "Discount Code is expired");
                            RequestDispatcher rd = request.getRequestDispatcher("400_Error.jsp");
                            rd.forward(request, response);
                        }
                        request.setAttribute("Error_Massage", "Invalid Discount Code");
                        RequestDispatcher rd = request.getRequestDispatcher("400_Error.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    Shopping_WebSite.get_Shopping_WebSite_instance().add_to_purchased_list(username, 0);
                }
                if (action.equals("remove_from_buyList")) {
                    int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));
                    Shopping_WebSite.get_Shopping_WebSite_instance().remove_from_buy_list(username, commodity_id);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
            rd.forward(request, response);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/login");
        rd.forward(request, response);
    }
}