package com.example.baloot_shoppingcenter_phase2.servlets;

import com.example.baloot_shoppingcenter_phase2.Shopping_WebSite;
import com.example.baloot_shoppingcenter_phase2.User;
import com.example.baloot_shoppingcenter_phase2.User_Discount;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String current_logged_in_user_name = Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name();
        if(current_logged_in_user_name != null){
            String action = request.getParameter("action");
            if(action != null){
                if(action.equals("payment")){
                    String discountCode = request.getParameter("discountCode");
                    int discount = Integer.parseInt(request.getParameter("discount"));
                    int credit = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(current_logged_in_user_name).get_credit();
                    int total_buy_list_price = (Shopping_WebSite.get_Shopping_WebSite_instance().get_total_buy_list_price(current_logged_in_user_name) * (100 - discount))/100;
                    if(credit >= total_buy_list_price){
                        Shopping_WebSite.get_Shopping_WebSite_instance().add_to_purchased_list(current_logged_in_user_name, discount);
                        Shopping_WebSite.get_Shopping_WebSite_instance().get_User(current_logged_in_user_name).expire_discount_from_user_discounts(discountCode);
                        request.setAttribute("Success_Massage", "Buy List Payed Successfully");
                        RequestDispatcher rd = request.getRequestDispatcher("200_Success.jsp");
                        rd.forward(request, response);
                    }
                    request.setAttribute("Error_Massage", "Credit Is Not Enough");
                    RequestDispatcher rd = request.getRequestDispatcher("400_Error.jsp");
                    rd.forward(request, response);
                }
                if(action.equals("remove")){
                    int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));
                    Shopping_WebSite.get_Shopping_WebSite_instance().remove_from_buy_list(current_logged_in_user_name, commodity_id);
                    RequestDispatcher rd = request.getRequestDispatcher("buyList.jsp");
                    rd.forward(request, response);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("buyList.jsp");
            rd.forward(request, response);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/login");
        rd.forward(request, response);
    }
}