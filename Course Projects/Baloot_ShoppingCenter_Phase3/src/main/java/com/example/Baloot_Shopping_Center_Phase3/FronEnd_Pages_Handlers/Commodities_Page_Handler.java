package com.example.Baloot_Shopping_Center_Phase3.FronEnd_Pages_Handlers;

import com.example.Baloot_Shopping_Center_Phase3.Commodity;
import com.example.Baloot_Shopping_Center_Phase3.Shopping_WebSite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Commodities_Page_Handler {
    public String Response(int number_of_current_page, String current_logged_in_username, boolean sort_by_price, boolean sort_by_name, String name, String category) throws IOException {
        List<Commodity> Commodities = new ArrayList<>();
        if(!name.isEmpty())
            Commodities = Shopping_WebSite.get_Shopping_WebSite_instance().get_Commodities_with_name(name);
        if(!category.isEmpty())
            Commodities = Shopping_WebSite.get_Shopping_WebSite_instance().get_Commodities_with_category(category);
        if(name.isEmpty() && category.isEmpty())
            Commodities = Shopping_WebSite.get_Shopping_WebSite_instance().get_all_Commodities();
        if(sort_by_name)
            for(int i = 0; i < 11; i++) {
                for(int j = 0; j < 11; j++) {
                    if(Commodities.get((12 * (number_of_current_page - 1)) + j).get_name().compareTo((Commodities.get((12 * (number_of_current_page - 1)) + j + 1).get_name())) > 0) {
                        Commodity temp = Commodities.get((12 * (number_of_current_page - 1)) + j);
                        Commodities.set((12 * (number_of_current_page - 1)) + j, Commodities.get((12 * (number_of_current_page - 1)) + j + 1));
                        Commodities.set((12 * (number_of_current_page - 1)) + j + 1, temp);
                    }
                }
            }
        if(sort_by_price)
            for(int i = 0; i < 11; i++) {
                for(int j = 0; j < 11; j++) {
                    if(Commodities.get((12 * (number_of_current_page - 1)) + j).get_price() > (Commodities.get((12 * (number_of_current_page - 1)) + j + 1).get_price())) {
                        Commodity temp = Commodities.get((12 * (number_of_current_page - 1)) + j);
                        Commodities.set((12 * (number_of_current_page - 1)) + j, Commodities.get((12 * (number_of_current_page - 1)) + j + 1));
                        Commodities.set((12 * (number_of_current_page - 1)) + j + 1, temp);
                    }
                }
            }
        String Response =
                "<!Doctype html>" +

                        "<html>" +

                            "<head>" +
                                "<title>Commodities</title>" +
                                "<link rel=\"stylesheet\" href=\"css/Footer - css.css\" />" +
                                "<link rel=\"stylesheet\" href=\"css/Normalize - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Home Header - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Sort Commodities - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Commodities - page - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Card - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"css/Pagination - css.css\">" +
                                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\">" +
                                "<script src=\"https://code.jquery.com/jquery-3.4.1.min.js\"></script>" +
                                "<script src=\"https://unpkg.com/react@16/umd/react.development.js\"></script>" +
                                "<script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\"></script>" +
                                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.10.3/babel.min.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Card.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Home-Header.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Sort-Commodities-Bar.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Pagination.js\"></script>" +
                                "<script type=\"text/babel\" src=\"js/Footer.js\"></script>" +
                            "</head>" +

                            "<body>" +

                                "<div class=\"background\"></div>" +

                                "<div id=\"sort-commodities-bar\"></div>" +

                                "<table>";
                                for(int row = 0; row < 3; row++) {
                                    Response += "<tr>";
                                    for(int column = 0; column < 4; column++) {
                                        if(Commodities.size() > row * 4 + column){
                                            Response +=
                                                    "<td>" +
                                                            "<div id=\"card" + row + column + "\"></div>" +
                                                            "<script type=\"text/babel\">" +
                                                                "ReactDOM.render(<Card commodity_id=\"" + Commodities.get((12 * (number_of_current_page - 1)) + (row * 4 + column)).get_id()  + "\" current_logged_in_username=\"" + current_logged_in_username + "\"/>, document.getElementById(\'card" + row + column + "\'));" +
                                                            "</script>" +
                                                    "</td>";
                                        }
                                    }
                                    Response += "</tr>";
                                }
                                Response += "</table>" +

                                "<div id=\"pagination\"></div>" +

                                "<div id=\"footer\"></div>" +

                                "<div id=\"home-header\"></div>" +

                                "<script type=\"text/babel\">" +
                                    "ReactDOM.render(<HomeHeader />, document.getElementById(\'home-header\'));" +
                                    "ReactDOM.render(<SortCommoditiesBar number_of_current_page=\"" + number_of_current_page + "\" current_logged_in_username=\"" + current_logged_in_username + "\"/>, document.getElementById(\'sort-commodities-bar\'));" +
                                    "ReactDOM.render(<Pagination number_of_current_page=\"" + number_of_current_page + "\" number_of_end_page=\"" + (Commodities.size() / 12 + 1) + "\" current_logged_in_username=\"" + current_logged_in_username + "\"/>, document.getElementById(\'pagination\'));" +
                                    "ReactDOM.render(<Footer />, document.getElementById(\'footer\'));" +
                                "</script>" +

                            "</body>" +

                        "</html>";
        return Response;
    }
}