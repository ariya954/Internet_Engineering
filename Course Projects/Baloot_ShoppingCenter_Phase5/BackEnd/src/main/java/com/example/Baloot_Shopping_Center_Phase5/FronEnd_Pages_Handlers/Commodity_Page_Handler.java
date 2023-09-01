package com.example.Baloot_Shopping_Center_Phase5.FronEnd_Pages_Handlers;

import com.example.Baloot_Shopping_Center_Phase5.Shopping_WebSite;
import com.example.Baloot_Shopping_Center_Phase5.User_Comment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Commodity_Page_Handler {
    public String Response(int product_id, String current_logged_in_username, boolean add_to_cart, boolean remove_from_cart, String comment, int comment_id, int comment_rate, int product_rate) throws IOException, SQLException {
        if(add_to_cart)
            Shopping_WebSite.get_Shopping_WebSite_instance().add_to_buy_list(current_logged_in_username, product_id);
        if(remove_from_cart)
            Shopping_WebSite.get_Shopping_WebSite_instance().get_User(current_logged_in_username).decrease_in_cart_of_buy_list(product_id);
        if(!comment.isEmpty())
            Shopping_WebSite.get_Shopping_WebSite_instance().add_comment(comment, current_logged_in_username, product_id);
        if((comment_rate == -1) || (comment_rate == 1))
            Shopping_WebSite.get_Shopping_WebSite_instance().rate_comment(current_logged_in_username, comment_id, comment_rate);
        if(product_rate > 0)
            Shopping_WebSite.get_Shopping_WebSite_instance().rate_commodity(current_logged_in_username, product_id, product_rate);
        int number_of_commodities_in_cart = Shopping_WebSite.get_Shopping_WebSite_instance().get_number_of_commodities_in_user_cart(current_logged_in_username);
        int number_of_this_commodity_in_cart = Shopping_WebSite.get_Shopping_WebSite_instance().get_number_of_this_commodity_in_user_cart(current_logged_in_username, product_id);
        int number_of_given_rates_to_this_commodity = Shopping_WebSite.get_Shopping_WebSite_instance().get_Commodity(product_id).get_number_of_users_rates();
        List<User_Comment> comments = Shopping_WebSite.get_Shopping_WebSite_instance().get_Comments_about(product_id);
        List<Integer> recommended_commodities =  Shopping_WebSite.get_Shopping_WebSite_instance().get_recommended_commodities_for(product_id);
        String product_provider_name = Shopping_WebSite.get_Shopping_WebSite_instance().get_Provider(Shopping_WebSite.get_Shopping_WebSite_instance().get_Commodity(product_id).get_provider_id()).get_name();
        String Response =
                "<!Doctype html>" +

                        "<html>" +

                            "<head>" +
                                "<title>Commodity</title>" +
                                "<link rel=\"stylesheet\" href=\"css/Footer - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Normalize - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Product Info - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/User Header - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Commodity - page - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Recommended Commodities - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Card - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Submit Opinion - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Comments - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\">" +
                                "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">" +
                                "<script src=\"https://code.jquery.com/jquery-3.4.1.min.js\"></script>" +
                                "<script src=\"https://unpkg.com/react@16/umd/react.development.js\"></script>" +
                                "<script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\"></script>" +
                                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.10.3/babel.min.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Submit-Opinion.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Product-Info.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/User-Header.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Comment.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Footer.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Card.js\"></script>" +

                            "</head>" +

                            "<body>" +

                                "<div class=\"background\"></div>" +

                                "<div id=\"product-info\"></div>" +

                                "<div class=\"comments\">";
                                    for(int i = 0; i < comments.size(); i++) {
                                        Response += "<ul>";
                                        if(i == 0)
                                            Response += "<li><label>Comments&nbsp</label><label class=\"gray\">(" + comments.size() + ")</label></li>";
                                        Response +=
                                                        "<div id=\"comment" + i + "\"></div>" +
                                                        "<script type=\"text/babel\">" +
                                                            "ReactDOM.render(<Comment commodity_id=\"" + product_id + "\" current_logged_in_username=\"" + current_logged_in_username + "\" comment_id=\"" + comments.get(i).get_id() + "\" number_of_likes=\"" + comments.get(i).get_number_of_likes() + "\"  number_of_dislikes=\"" + comments.get(i).get_number_of_dislikes() + "\" comment=\"" + comments.get(i).get_comment() + "\"" + " comment_date=\"" + comments.get(i).get_comment_date() + "\"" + " user_email=\"" + comments.get(i).get_user_email() + "\"" + "/>, document.getElementById(\'comment" + i + "\'));" +
                                                        "</script>" +
                                                    "</ul>";
                                    }
                                    Response +=
                                    "<ul>" +
                                        "<li><div id=\"submit-opinion\"></div></li>" +
                                    "</ul>" +
                                "</div>" +

                                "<div class=\"recommended-commodities\">" +
                                    "<h>You also might like...</h>" +
                                    "<table>" +
                                        "<tr>";
                                            for(int i = 0; i < recommended_commodities.size(); i++) {
                                                Response += "<td>" +
                                                        "<div id=\"card" + i + "\"></div>" +
                                                        "<script type=\"text/babel\">" +
                                                            "ReactDOM.render(<Card commodity_id=\"" + recommended_commodities.get(i) + "\"" + " current_logged_in_username=\"" + current_logged_in_username + "\"/>, document.getElementById(\'card" + i + "\'));" +
                                                        "</script>" +
                                                        "</td>";
                                            }
                                        Response += "</tr>" +
                                    "</table>" +
                                "</div>" +

                                "<div id=\"footer\"></div>" +

                                "<div id=\"user-header\"></div>" +

                                "<script type=\"text/babel\">" +
                                    "ReactDOM.render(<ProductInfo product_id=\"" + product_id + "\" product_provider=\"" + product_provider_name + "\" username=\"" + current_logged_in_username + "\" number_of_this_commodity_in_cart=\"" + number_of_this_commodity_in_cart + "\" number_of_rates=\"" + number_of_given_rates_to_this_commodity + "\"/>, document.getElementById(\'product-info\'));" +
                                    "ReactDOM.render(<UserHeader username=\"" + current_logged_in_username + "\" number_of_commodities_in_cart=\"" + number_of_commodities_in_cart + "\"/>, document.getElementById(\'user-header\'));" +
                                    "ReactDOM.render(<SubmitOpinion product_id=\"" + product_id + "\" username=\"" + current_logged_in_username + "\"/>, document.getElementById(\'submit-opinion\'));" +
                                    "ReactDOM.render(<Footer />, document.getElementById(\'footer\'));" +
                                "</script>" +

                            "</body>" +

                        "</html>";
        return Response;
    }
}