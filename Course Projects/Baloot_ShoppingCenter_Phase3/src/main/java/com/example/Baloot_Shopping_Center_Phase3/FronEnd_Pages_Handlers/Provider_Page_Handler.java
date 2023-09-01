package com.example.Baloot_Shopping_Center_Phase3.FronEnd_Pages_Handlers;

import com.example.Baloot_Shopping_Center_Phase3.Commodity;
import com.example.Baloot_Shopping_Center_Phase3.Shopping_WebSite;

import java.io.IOException;
import java.util.List;

public class Provider_Page_Handler {
    public String Response(int provider_id, String current_logged_in_username) throws IOException {
        int number_of_commodities_in_logged_in_user_cart = Shopping_WebSite.get_Shopping_WebSite_instance().get_number_of_commodities_in_user_cart(current_logged_in_username);
        List<Commodity> Provider_Commodities = Shopping_WebSite.get_Shopping_WebSite_instance().get_provider_commodities(provider_id);
        String Response =
                "<!Doctype html>" +

                        "<html>" +

                        "<head>" +
                            "<title>Provider</title>" +
                            "<link rel=\"stylesheet\" href=\"css/Footer - css.css\" />" +
                            "<link rel=\"stylesheet\" href=\"css/Normalize - css.css\">" +
                            "<link rel=\"stylesheet\" href=\"css/User Header - css.css\">" +
                            "<link rel=\"stylesheet\" href=\"css/Provider - page - css.css\">" +
                            "<link rel=\"stylesheet\" href=\"css/Card - css.css\">" +
                            "<script src=\"https://code.jquery.com/jquery-3.4.1.min.js\"></script>" +
                            "<script src=\"https://unpkg.com/react@16/umd/react.development.js\"></script>" +
                            "<script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\"></script>" +
                            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.10.3/babel.min.js\"></script>" +
                            "<script type=\"text/babel\" src=\"js/Card.js\"></script>" +
                            "<script type=\"text/babel\" src=\"js/Provider-Info.js\"></script>" +
                            "<script type=\"text/babel\" src=\"js/User-Header.js\"></script>" +
                            "<script type=\"text/babel\" src=\"js/Footer.js\"></script>" +
                        "</head>" +

                        "<body>" +

                        "<div class=\"background\"></div>" +

                        "<div id=\"provider-info\"></div>" +

                        "<div class=\"provided-commodities-table-title\">All provided commodities</div>" +

                        "<table class=\"provided-commodities-table\">";
                        for(int row = 0; row * 4 < Provider_Commodities.size(); row++) {
                            Response += "<tr>";
                            for(int column = 0; column < 4; column++) {
                                if(Provider_Commodities.size() > row * 4 + column){
                                    Response +=
                                            "<td>" +
                                                    "<div id=\"card" + row + column + "\"></div>" +
                                                    "<script type=\"text/babel\">" +
                                                        "ReactDOM.render(<Card commodity_id=\"" + Provider_Commodities.get(row * 4 + column).get_id()  + "\" current_logged_in_username=\"" + current_logged_in_username + "\"/>, document.getElementById(\'card" + row + column + "\'));" +
                                                    "</script>" +
                                            "</td>";
                                }
                            }
                            Response += "</tr>";
                        }
                        Response += "</table>" +

                        "<div id=\"footer\"></div>" +

                        "<div id=\"user-header\"></div>" +

                        "<script type=\"text/babel\">" +
                            "ReactDOM.render(<ProviderInfo provider_id=\"" + provider_id + "\"/>, document.getElementById(\'provider-info\'));" +
                            "ReactDOM.render(<UserHeader username=\"" + current_logged_in_username + "\" number_of_commodities_in_cart=\"" + number_of_commodities_in_logged_in_user_cart + "\"/>, document.getElementById(\'user-header\'));" +
                            "ReactDOM.render(<Footer />, document.getElementById(\'footer\'));" +
                        "</script>" +

                        "</body>" +

                "</html>";
        return Response;
    }
}