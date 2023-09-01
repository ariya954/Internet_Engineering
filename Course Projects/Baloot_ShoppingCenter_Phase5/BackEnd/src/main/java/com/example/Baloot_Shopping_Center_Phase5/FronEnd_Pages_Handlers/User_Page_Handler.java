package com.example.Baloot_Shopping_Center_Phase5.FronEnd_Pages_Handlers;

import com.example.Baloot_Shopping_Center_Phase5.Buy_List;
import com.example.Baloot_Shopping_Center_Phase5.Purchased_List;
import com.example.Baloot_Shopping_Center_Phase5.Shopping_WebSite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class User_Page_Handler {
    public String Response(String username, int credit_to_added, boolean pay, int commodity_id, boolean add_to_cart, boolean remove_from_cart) throws IOException, SQLException {
        if(credit_to_added > 0)
            Shopping_WebSite.get_Shopping_WebSite_instance().add_credit(username, credit_to_added);
        if(pay)
            Shopping_WebSite.get_Shopping_WebSite_instance().add_to_purchased_list(username);
        if(add_to_cart)
            Shopping_WebSite.get_Shopping_WebSite_instance().add_to_buy_list(username, commodity_id);
        if(remove_from_cart)
            Shopping_WebSite.get_Shopping_WebSite_instance().get_User(username).decrease_in_cart_of_buy_list(commodity_id);
        int number_of_commodities_in_cart = Shopping_WebSite.get_Shopping_WebSite_instance().get_number_of_commodities_in_user_cart(username);
        List<Buy_List> user_buy_list = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(username).get_User_Buy_List();
        List<Purchased_List> user_purchased_list = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(username).get_User_Purchased_List();
        String Response =
               "<!Doctype html>" +

                       "<html>" +

                           "<head>" +
                               "<title>User</title>" +
                               "<link rel=\"stylesheet\" href=\"css/Normalize - css.css\">" +
                               "<link rel=\"stylesheet\" href=\"css/User Info - css.css\">" +
                               "<link rel=\"stylesheet\" href=\"css/User Header - css.css\">" +
                               "<link rel=\"stylesheet\" href=\"css/User - page - css.css\">" +
                               "<link rel=\"stylesheet\" href=\"css/Cart - css.css\">" +
                               "<link rel=\"stylesheet\" href=\"css/History - css.css\">" +
                               "<link rel=\"stylesheet\" href=\"css/Footer - css.css\" />" +
                               "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\">" +
                               "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\">" +
	                           "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js\"></script>" +
	                           "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\"></script>" +
                               "<script src=\"https://unpkg.com/react@16/umd/react.development.js\"></script>" +
                               "<script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\"></script>" +
                               "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.10.3/babel.min.js\"></script>" +
                               "<script type=\"text/babel\" src=\"js/Purchased-List-Item.js\"></script>" +
                               "<script type=\"text/babel\" src=\"js/Buy-List-Item.js\"></script>" +
                               "<script type=\"text/babel\" src=\"js/User-Header.js\"></script>" +
                               "<script type=\"text/babel\" src=\"js/User-Info.js\"></script>" +
                               "<script type=\"text/babel\" src=\"js/Footer.js\"></script>" +
                               "<script type=\"text/babel\" src=\"js/Pay.js\"></script>" +
                               "<script>" +
                               "function pay_button_click_event() {" +
                                    "const pay_button = document.getElementById(\"PayButton\");" +
                                        "pay_button.innerHTML = \"Loading&nbsp&nbsp\";" +
                                        "pay_button.style.color = \"white\";" +
                                        "pay_button.style.background = \"#909090\";" +
                               "}" +
                               "</script>" +
                           "</head>" +

                           "<body>" +

                               "<div class=\"background\"></div>" +

                               "<div id=\"user-header\"></div>" +

                               "<div id=\"user-info\"></div>" +

                               "<div class=\"cart-table\">" +
                                    "<i class=\"bi bi-cart3\"> Cart</i>" +
                                    "<table>" +
                                        "<table>" +
                                            "<tr>" +
                                                "<td><b>Image</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Name</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Categories</b></td><td>&nbsp<td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Price</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Provider ID</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Rating</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>In Stock</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>In Cart</b></td>" +
                                            "</tr>" +
                                        "</table>";
                                        for(int buy_list_item = 0; buy_list_item < user_buy_list.size(); buy_list_item++) {
                                            Response +=
                                                    "<tr>" +
                                                            "<div id=\"buy-list-item" + buy_list_item + "\"></div>" +
                                                            "<script type=\"text/babel\">" +
                                                                "ReactDOM.render(<BuyListItem buy_list_item_id=\"" + user_buy_list.get(buy_list_item).Commodity  + "\" number_of_buy_list_item_in_cart=\"" + user_buy_list.get(buy_list_item).in_cart + "\" current_logged_in_user=\"" + username + "\"/>, document.getElementById(\'buy-list-item" + buy_list_item + "\'));" +
                                                            "</script>" +
                                                    "</tr>";
                                        }
                                        Response +=
                                    "</table>" +
                               "</div>" +

                               "<div id=\"pay\"></div>" +

                               "<div class=\"history-table\">"  +
                                    "<i class=\"bi bi-clock-history\"> History</i>" +
                                    "<table>" +
                                        "<table>" +
                                            "<tr>" +
                                                "<td><b>Image</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Name</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Categories</b></td><td>&nbsp<td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Price</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Provider ID</b></td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Rating</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>In Stock</b></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>" +
                                                "<td><b>Quantity</b></td>" +
                                            "</tr>" +
                                        "</table>";
                                        for(int purchased_list_item = 0; purchased_list_item < user_purchased_list.size(); purchased_list_item++) {
                                            Response +=
                                                    "<tr>" +
                                                            "<div id=\"purchased-list-item" + purchased_list_item + "\"></div>" +
                                                            "<script type=\"text/babel\">" +
                                                                "ReactDOM.render(<PurchasedListItem purchased_list_item_id=\"" + user_purchased_list.get(purchased_list_item).Commodity  + "\" number_of_purchased=\"" + user_purchased_list.get(purchased_list_item).number_of_purchased + "\"/>, document.getElementById(\'purchased-list-item" + purchased_list_item + "\'));" +
                                                            "</script>" +
                                                    "</tr>";
                                        }
                                        Response +=
                                    "</table>" +
                               "</div>" +

                               "<div id=\"footer\"></div>" +

                               "<script type=\"text/babel\">" +
                                    "ReactDOM.render(<UserInfo username=\"" + username + "\"/>, document.getElementById(\'user-info\'));" +
                                    "ReactDOM.render(<UserHeader username=\"" + username + "\" number_of_commodities_in_cart=\"" + number_of_commodities_in_cart + "\"/>, document.getElementById(\'user-header\'));" +
                                    "ReactDOM.render(<Pay username=\"" + username + "\"/>, document.getElementById(\'pay\'));" +
                                    "ReactDOM.render(<Footer />, document.getElementById(\'footer\'));" +
                               "</script>" +

                           "</body>" +

                       "</html>";
        return Response;
    }
}