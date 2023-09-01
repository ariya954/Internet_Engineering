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

public class Discount_Validation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logged_in_user_name = Shopping_WebSite.get_Shopping_WebSite_instance().get_current_logged_in_user_name();
        if(logged_in_user_name != null) {
            User logged_in_user = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(logged_in_user_name);
            String discountCode = request.getParameter("discount_code");
            List<User_Discount> User_Discounts = logged_in_user.get_User_Discounts();
            for (int index_of_user_discounts = 0; index_of_user_discounts < User_Discounts.size(); index_of_user_discounts++)
                if (User_Discounts.get(index_of_user_discounts).get_discountCode().equals(discountCode)) {
                    if (User_Discounts.get(index_of_user_discounts).is_expired() == 0) {
                        request.setAttribute("discountCode", User_Discounts.get(index_of_user_discounts).get_discountCode());
                        request.setAttribute("discount", User_Discounts.get(index_of_user_discounts).get_discount());
                        RequestDispatcher rd = request.getRequestDispatcher("buyList.jsp");
                        rd.forward(request, response);
                    }
                    request.setAttribute("Error_Massage", "Discount is expired");
                    RequestDispatcher rd = request.getRequestDispatcher("400_Error.jsp");
                    rd.forward(request, response);
                }
            request.setAttribute("Error_Massage", "Discount Code Not Found");
            RequestDispatcher rd = request.getRequestDispatcher("400_Error.jsp");
            rd.forward(request, response);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/login");
        rd.forward(request, response);
    }
}