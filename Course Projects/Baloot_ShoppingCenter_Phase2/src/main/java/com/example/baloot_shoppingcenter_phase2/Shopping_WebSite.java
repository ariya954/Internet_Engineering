package com.example.baloot_shoppingcenter_phase2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Shopping_WebSite {
    private static Shopping_WebSite instance;
    private List<User> Users = new ArrayList<>();
    private List<Provider> Providers = new ArrayList<>();
    private List<Commodity> Commodities = new ArrayList<>();
    private List<User_Comment> Users_Comments = new ArrayList<>();

    private List<User_Discount> Users_Discounts = new ArrayList<>();

    private String current_logged_in_user_name;

    public Shopping_WebSite(){}

    public static Shopping_WebSite get_Shopping_WebSite_instance() throws IOException {
        if(instance == null){
            instance = new Shopping_WebSite();
            instance.get_users_from_external_server("http://5.253.25.110:5000/api/users");
            instance.get_providers_from_external_server("http://5.253.25.110:5000/api/providers");
            instance.get_commodities_from_external_server("http://5.253.25.110:5000/api/commodities");
            instance.get_users_comments_from_external_server("http://5.253.25.110:5000/api/comments");
            instance.get_users_discounts_from_external_server("http://5.253.25.110:5000/api/discount");
        }
        return instance;
    }

    public String get_current_logged_in_user_name() {
        return current_logged_in_user_name;
    }

    public User get_User(String username){
        for (int index_of_User = 0; index_of_User < Users.size(); index_of_User++)
            if (Users.get(index_of_User).get_username().equals(username))
                return Users.get(index_of_User);
        return null;
    }

    public int get_User_Buy_List_Price(String username) {
        int total_Buy_List_Price = 0;
        List<Buy_List> User_Buy_List = get_User(username).get_User_Buy_List();
        for (int index_of_User_Buy_List = 0; index_of_User_Buy_List < User_Buy_List.size(); index_of_User_Buy_List++)
            total_Buy_List_Price += get_Commodity(User_Buy_List.get(index_of_User_Buy_List).Commodity).get_price();
        return total_Buy_List_Price;
    }

    public User_Discount get_User_Discount(String discount_code) {
        for (int index_of_User_Discount = 0; index_of_User_Discount < Users_Discounts.size(); index_of_User_Discount++)
            if(Users_Discounts.get(index_of_User_Discount).get_discount_code().equals(discount_code))
                return Users_Discounts.get(index_of_User_Discount);
        return null;
    }

    public Provider get_Provider(int id){
        for (int index_of_Provider = 0; index_of_Provider < Providers.size(); index_of_Provider++)
            if (Providers.get(index_of_Provider).get_id() == id)
                return Providers.get(index_of_Provider);
        return null;
    }

    public Commodity get_Commodity(int id){
        for (int index_of_Commodity = 0; index_of_Commodity < Commodities.size(); index_of_Commodity++)
            if (Commodities.get(index_of_Commodity).get_id() == id)
                return Commodities.get(index_of_Commodity);
        return null;
    }

    public void get_Commodities_List(){
        List<String> commodities_properties = new ArrayList<>();
        for (int index_of_Commodity = 0; index_of_Commodity < Commodities.size(); index_of_Commodity++) {
            commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_id()));
            commodities_properties.add(Commodities.get(index_of_Commodity).get_name());
            commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_provider_id()));
            commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_price()));
            commodities_properties.add(Commodities.get(index_of_Commodity).get_categories().get(0));
            commodities_properties.add(Commodities.get(index_of_Commodity).get_categories().get(1));
            commodities_properties.add(Float.toString(Commodities.get(index_of_Commodity).get_rating()));
            commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_inStock()));
        }
        print_outputs("print all commodities", commodities_properties);
    }

    public void get_Commodity_by_id(int id){
        List<String> commodity_properties = new ArrayList<>();
        for (int index_of_Commodity = 0; index_of_Commodity < Commodities.size(); index_of_Commodity++)
            if (Commodities.get(index_of_Commodity).get_id() == id) {
                commodity_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_id()));
                commodity_properties.add(Commodities.get(index_of_Commodity).get_name());
                commodity_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_provider_id()));
                commodity_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_price()));
                commodity_properties.add(Commodities.get(index_of_Commodity).get_categories().get(0));
                commodity_properties.add(Commodities.get(index_of_Commodity).get_categories().get(1));
                commodity_properties.add(Float.toString(Commodities.get(index_of_Commodity).get_rating()));
                print_outputs("print single commodity", commodity_properties);
                return;
            }
        System.out.println("Commodity doesn't exists");
    }

    public void get_Commodity_by_category(List<String> category){
        List<String> commodities_properties = new ArrayList<>();
        for (int index_of_Commodity = 0; index_of_Commodity < Commodities.size(); index_of_Commodity++)
            if (Commodities.get(index_of_Commodity).get_categories() == category) {
                commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_id()));
                commodities_properties.add(Commodities.get(index_of_Commodity).get_name());
                commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_provider_id()));
                commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_price()));
                commodities_properties.add(Commodities.get(index_of_Commodity).get_categories().get(0));
                commodities_properties.add(Commodities.get(index_of_Commodity).get_categories().get(1));
                commodities_properties.add(Float.toString(Commodities.get(index_of_Commodity).get_rating()));
                commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_inStock()));
                print_outputs("print list of commodities", commodities_properties);
                return;
            }
    }

    public void get_Buy_List(String username){
        List<String> commodities_properties = new ArrayList<>();
        for (int index_of_User = 0; index_of_User < Users.size(); index_of_User++)
            if (Users.get(index_of_User).get_username().equals(username)) {
                List<Buy_List> User_Buy_List = Users.get(index_of_User).get_User_Buy_List();
                for (int index_of_Buy_List = 0; index_of_Buy_List < User_Buy_List.size(); index_of_Buy_List++)
                    for (int index_of_Commodity = 0; index_of_Commodity < Commodities.size(); index_of_Commodity++)
                        if (Commodities.get(index_of_Commodity).get_id() == User_Buy_List.get(index_of_Buy_List).Commodity) {
                            commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_id()));
                            commodities_properties.add(Commodities.get(index_of_Commodity).get_name());
                            commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_provider_id()));
                            commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_price()));
                            commodities_properties.add(Commodities.get(index_of_Commodity).get_categories().get(0));
                            commodities_properties.add(Commodities.get(index_of_Commodity).get_categories().get(1));
                            commodities_properties.add(Float.toString(Commodities.get(index_of_Commodity).get_rating()));
                            commodities_properties.add(Integer.toString(Commodities.get(index_of_Commodity).get_inStock()));
                            break;
                        }
                print_outputs("print list of commodities", commodities_properties);
                return;
            }
    }

    public void change_current_logged_in_user_name(String new_logged_in_user_name) {
        current_logged_in_user_name = new_logged_in_user_name;
    }

    public void add_user(String given_username, String given_password, String given_email, String given_birthDate, String given_address, int given_credit){
        Pattern name_pattern = Pattern.compile("^[A-Za-z0-9]++$");
        if(!name_pattern.matcher(given_username).matches())
            System.out.println("Invalid username");
        else {
            for (int index_of_User = 0; index_of_User < Users.size(); index_of_User++)
                if (Users.get(index_of_User).get_username().equals(given_username)) {
                    User updated_user = new User(given_username, given_password, given_email, given_birthDate, given_address, given_credit);
                    Users.set(index_of_User, updated_user);
                    return;
                }
            User New_User = new User(given_username, given_password, given_email, given_birthDate, given_address, given_credit);
            Users.add(New_User);
        }
    }

    public void add_provider(int given_id, String given_name, String given_registryDate){
        Pattern name_pattern = Pattern.compile("^[A-Za-z0-9]++$");
        if(!name_pattern.matcher(given_name).matches())
            System.out.println("Invalid username");
        else {
            for (int index_of_provider = 0; index_of_provider < Providers.size(); index_of_provider++)
                if (Providers.get(index_of_provider).get_id() == given_id) {
                    Provider updated_provider = new Provider(given_id, given_name, given_registryDate);
                    Providers.set(index_of_provider, updated_provider);
                    return;
                }
            Provider New_Provider = new Provider(given_id, given_name, given_registryDate);
            Providers.add(New_Provider);
        }
    }

    public void add_commodity(int given_id, String given_name, int given_provider_id, int given_price, List<String> given_categories, float given_rating, int given_inStock){
        for (int index_of_provider = 0; index_of_provider < Providers.size(); index_of_provider++)
            if (Providers.get(index_of_provider).get_id() == given_provider_id) {
                for (int index_of_commodity = 0; index_of_commodity < Commodities.size(); index_of_commodity++)
                    if (Commodities.get(index_of_commodity).get_id() == given_id) {
                        Commodity updated_commodity = new Commodity(given_id, given_name, given_provider_id, given_price, given_categories, given_rating, given_inStock);
                        Commodities.set(index_of_commodity, updated_commodity);
                    }
                Commodity New_Commodity = new Commodity(given_id, given_name, given_provider_id, given_price, given_categories, given_rating, given_inStock);
                Commodities.add(New_Commodity);
                return;
            }
        System.out.println("Provider doesn't exists");
    }

    public void rate_commodity(String given_username, int given_commodity_id, int given_rate){
        for (int index_of_user = 0; index_of_user < Users.size(); index_of_user++)
            if (Users.get(index_of_user).get_username().equals(given_username)) {
                for (int index_of_commodity = 0; index_of_commodity < Commodities.size(); index_of_commodity++)
                    if (Commodities.get(index_of_commodity).get_id() == given_commodity_id) {
                        Commodities.get(index_of_commodity).add_user_rate(given_username, given_rate);
                        return;
                    }
                System.out.println("Commodity doesn't exists");
                return;
            }
        System.out.println("User doesn't exists");
    }

    public void rate_comment(String given_username, int given_comment_id, int given_rate){
        for (int index_of_users_comments = 0; index_of_users_comments < Users_Comments.size(); index_of_users_comments++)
            if (index_of_users_comments == given_comment_id) {
                Users_Comments.get(index_of_users_comments).add_user_rate(given_username, given_rate);
                return;
            }
        System.out.println("Comment doesn't exists");
    }

    public void add_user_comment(String given_comment, String given_email, int commodity_id, String comment_date){
        for (int index_of_users_commnets = 0; index_of_users_commnets < Users_Comments.size(); index_of_users_commnets++)
            if (Users_Comments.get(index_of_users_commnets).get_user_email().equals(given_email)) {
                User_Comment updated_comment = new User_Comment(given_email, commodity_id, given_comment, comment_date);
                Users_Comments.set(index_of_users_commnets, updated_comment);
                return;
            }
        Users_Comments.add(new User_Comment(given_email, commodity_id, given_comment, comment_date));
    }

    public void add_to_buy_list(String given_username, int given_commodity_id){
        for (int index_of_user = 0; index_of_user < Users.size(); index_of_user++)
            if (Users.get(index_of_user).get_username().equals(given_username)) {
                for (int index_of_commodity = 0; index_of_commodity < Commodities.size(); index_of_commodity++)
                    if (Commodities.get(index_of_commodity).get_id() == given_commodity_id) {
                        if(Commodities.get(index_of_commodity).get_inStock() > 0){
                            Users.get(index_of_user).add_commodity_to_user_buy_list(given_commodity_id);;
                            return;
                        }
                        System.out.println("There is no Commodity available in stock");
                        return;
                    }
                System.out.println("Commodity doesn't exists");
                return;
            }
        System.out.println("User doesn't exists");
    }

    public void add_to_purchased_list(String given_username, int discount){
        for (int index_of_user = 0; index_of_user < Users.size(); index_of_user++)
            if (Users.get(index_of_user).get_username().equals(given_username)) {
                List<Buy_List> user_buy_list = Users.get(index_of_user).get_User_Buy_List();
                for(int i = 0; i < user_buy_list.size(); i++)
                    for (int j = 0; j < Commodities.size(); j++)
                        if (Commodities.get(j).get_id() == user_buy_list.get(i).Commodity) {
                            if (Commodities.get(j).get_inStock() == 0) {
                                System.out.println("There is no commodity available in stock");
                                return;
                            } else {
                                Commodities.get(j).decrease_inStock(1);
                                Users.get(index_of_user).decrease_credit((Commodities.get(j).get_price() * (100 - discount)) / 100);
                                Users.get(index_of_user).add_commodity_to_user_purchased_list(Commodities.get(j).get_id());
                                this.remove_from_buy_list(Users.get(index_of_user).get_username(), Commodities.get(j).get_id());
                                if(i >= user_buy_list.size())
                                    return;
                            }
                        }
                return;
            }
        System.out.println("User doesn't exists");
    }

    public void remove_from_buy_list(String given_username, int given_commodity_id){
        for (int index_of_user = 0; index_of_user < Users.size(); index_of_user++)
            if (Users.get(index_of_user).get_username().equals(given_username)) {
                for (int index_of_commodity = 0; index_of_commodity < Commodities.size(); index_of_commodity++)
                    if (Commodities.get(index_of_commodity).get_id() == given_commodity_id) {
                        Users.get(index_of_user).remove_commodity_from_user_buy_list(given_commodity_id);
                        return;
                    }
                System.out.println("Commodity doesn't exists");
                return;
            }
        System.out.println("User doesn't exists");
    }

    public void print_outputs(String print_type, List<String> outputs){
        if(print_type == "print single commodity"){
            System.out.println("data: {\"id\": " + outputs.get(0) +
                    ", \"name\": \"" + outputs.get(1) + '\"' +
                    ", \"provider\": " + outputs.get(2) +
                    ", \"price\": "+ outputs.get(3) +
                    ", \"categories\": [\"" + outputs.get(4) + "\", \"" + outputs.get(5) + "\"]" +
                    ", \"rating\": " + outputs.get(6) + '}');
        }
        if(print_type == "print all commodities"){
            System.out.print("\"data\": {\"commoditiesList\": [");
            for (int i = 0; i < outputs.size(); i += 8){
                System.out.print("{\"id\": " + outputs.get(i) +
                        ", \"name\": \"" + outputs.get(i + 1) + '\"' +
                        ", \"providerId\": " + outputs.get(i + 2) +
                        ", \"price\": "+ outputs.get(i + 3) +
                        ", \"categories\": [\"" + outputs.get(i + 4) + "\", \"" + outputs.get(i + 5) + "\"]" +
                        ", \"rating\": " + outputs.get(i + 6) +
                        ", \"inStock\": "+ outputs.get(i + 7) + '}');
            }
            System.out.println("]}");
        }
        if(print_type == "print list of commodities"){
            System.out.print("\"data\": {\"commoditiesList\": [");
            for (int i = 0; i < outputs.size(); i += 8){
                System.out.print("{\"id\": " + outputs.get(i) +
                        ", \"name\": \"" + outputs.get(i + 1) + '\"' +
                        ", \"providerId\": " + outputs.get(i + 2) +
                        ", \"price\": "+ outputs.get(i + 3) +
                        ", \"categories\": [\"" + outputs.get(i + 4) + "\", \"" + outputs.get(i + 5) + "\"]" +
                        ", \"rating\": " + outputs.get(i + 6) +
                        ", \"inStock\": "+ outputs.get(i + 7) + '}');
            }
            System.out.println("]}");
        }
    }

    public void get_users_from_external_server(String external_server_url) throws IOException {
        List<User> available_users = new ObjectMapper().readValue(new URL(external_server_url), new TypeReference<List<User>>() {});
        for(int i = 0; i < available_users.size(); i++){
            Users.add(available_users.get(i));
        }
    }

    public void get_providers_from_external_server(String external_server_url) throws IOException {
        List<Provider> available_providers = new ObjectMapper().readValue(new URL(external_server_url), new TypeReference<List<Provider>>() {});
        for(int i = 0; i < available_providers.size(); i++){
            Providers.add(available_providers.get(i));
        }
    }

    public void get_commodities_from_external_server(String external_server_url) throws IOException {
        List<Commodity> available_commodities = new ObjectMapper().readValue(new URL(external_server_url), new TypeReference<List<Commodity>>() {});
        for(int i = 0; i < available_commodities.size(); i++){
            Commodities.add(available_commodities.get(i));
        }
    }

    public void get_users_comments_from_external_server(String external_server_url) throws IOException {
        List<User_Comment> available_users_comments = new ObjectMapper().readValue(new URL(external_server_url), new TypeReference<List<User_Comment>>() {});
        for(int i = 0; i < available_users_comments.size(); i++){
            Users_Comments.add(available_users_comments.get(i));
        }
    }

    public void get_users_discounts_from_external_server(String external_server_url) throws IOException {
        List<User_Discount> available_discounts = new ObjectMapper().readValue(new URL(external_server_url), new TypeReference<List<User_Discount>>() {});
        for(int i = 0; i < available_discounts.size(); i++){
            Users_Discounts.add(available_discounts.get(i));
        }
    }

    public String show_user_page_handler(String username){
        User user = get_User(username);
        String Response =
                        "<ul>" +
                        "<li id=\"username\">Username: " + user.get_username() + "</li>" +
                        "<li id=\"email\">Email: " + user.get_email() + "</li>" +
                        "<li id=\"birthDate\">Birth Date: " + user.get_birthDate() + "</li>" +
                        "<li id=\"address\">Address: " + user.get_address() + "</li>" +
                        "<li id=\"credit\">Credit: " +  Integer.toString(user.get_credit()) + "</li>" +
                        "<li>Current Buy List Price: " +  Integer.toString(get_User_Buy_List_Price(user.get_username())) + "</li>" +
                        "<li><a href=\"/Baloot_ShoppingCenter_Phase2_war_exploded/credit\">Add Credit</a></li>" +
                        "<li>" +
                        "<form action=\"/Baloot_ShoppingCenter_Phase2_war_exploded/user\" method=\"GET\">" +
                        "<input type=\"hidden\" name=\"action\" value=\"pay\">" +
                        "Discount Code: <input type=\"text\" name=\"discount_code\"></input>" +
                        "<br>" +
                        "<label>Buy List Payment: </label>" +
                        "<button type=\"submit\">Pay</button>" +
                        "</form>" +
                        "</li>" +
                        "</ul>" +
                        "<table>" +
                        "<caption>" +
                        "<h2>Buy List</h2>" +
                        "</caption>" +
                        "<tr>" +
                        "<th>Id</th>" +
                        "<th>Name</th>" +
                        "<th>Provider Id</th>" +
                        "<th>Price</th>" +
                        "<th>Categories</th>" +
                        "<th>Rating</th>" +
                        "<th>In Stock</th>" +
                        "<th></th>" +
                        "<th></th>" +
                        "</tr>";
        List<Buy_List> user_Buy_List = user.get_User_Buy_List();
        for(int i = 0; i < user_Buy_List.size(); i++) {
            Response += "<tr>";
            for(int j = 0; j < Commodities.size(); j++){
                if (Commodities.get(j).get_id() == user_Buy_List.get(i).Commodity){
                    Response +=
                            "<th>" + Integer.toString(Commodities.get(j).get_id()) + "</th>" +
                                    "<th>" + Commodities.get(j).get_name() + "</th>" +
                                    "<th>" + Integer.toString(Commodities.get(j).get_provider_id()) + "</th>" +
                                    "<th>" + Integer.toString(Commodities.get(j).get_price()) + "</th>" +
                                    "<th>" + Commodities.get(j).get_categories() + "</th>" +
                                    "<th>" + Float.toString(Commodities.get(j).get_rating()) + "</th>" +
                                    "<th>" + Integer.toString(Commodities.get(j).get_inStock()) + "</th>" +
                                    "<td><a href=\"" + "/Baloot_ShoppingCenter_Phase2_war_exploded/commodities/{" + Commodities.get(i).get_id() + "}\"" + ">Link</a></td>" +
                                    "<td>" +
                                    "<form action=\"/Baloot_ShoppingCenter_Phase2_war_exploded/user\" method=\"GET\">" +
                                    "<input type=\"hidden\" name=\"action\" value=\"remove_from_buyList\">" +
                                    "<input type=\"hidden\" name=\"commodity_id\" value=\"" + Commodities.get(j).get_id() + "\">" +
                                    "<button type=\"submit\">Remove</button>" +
                                    "</form>" +
                                    "</td>" +
                                    "</tr>";
                }
            }
        }
        Response +=
                "</table>" +
                        "<br>" +
                        "<table>" +
                        "<caption><h2>Purchased List</h2></caption>" +
                        "<tr>" +
                        "<th>Id</th>" +
                        "<th>Name</th>" +
                        "<th>Provider Id</th>" +
                        "<th>Price</th>" +
                        "<th>Categories</th>" +
                        "<th>Rating</th>" +
                        "<th>In Stock</th>" +
                        "<th></th>" +
                        "</tr>";
        List<Integer> user_purchased_list = user.get_User_Purchased_List() ;
        for(int i = 0; i < user_purchased_list.size(); i++){
            for(int j = 0; j < Commodities.size(); j++)
                if(Commodities.get(j).get_id() == user_purchased_list.get(i)) {
                    Response += "<tr>" +
                            "<th>" + Integer.toString(Commodities.get(j).get_id()) + "</th>" +
                            "<th>" + Commodities.get(j).get_name() + "</th>" +
                            "<th>" + Integer.toString(Commodities.get(j).get_provider_id()) + "</th>" +
                            "<th>" + Integer.toString(Commodities.get(j).get_price()) + "</th>" +
                            "<th>" + Commodities.get(j).get_categories() + "</th>" +
                            "<th>" + Float.toString(Commodities.get(j).get_rating()) + "</th>" +
                            "<th>" + Integer.toString(Commodities.get(j).get_inStock()) + "</th>" +
                            "<td><a href=\"" + "/Baloot_ShoppingCenter_Phase2_war_exploded/commodities/{" + Commodities.get(i).get_id() + "}\"" + ">Link</a></td>" +
                            "</tr>";
                }
        }
        Response +=     "</table>" +
                "</body>" +
                "</html>";
        return Response;
    }

    public String add_credit_page_handler(String Path){
        String[] Path_Parameters = Path.split("/");
        String username = Path_Parameters[2].substring(3, Path_Parameters[2].length() - 3);
        User user = get_User(username);
        int amount_of_credit_to_add = Integer.parseInt(Path_Parameters[3].substring(3, Path_Parameters[3].length() - 3));
        user.increase_credit(amount_of_credit_to_add);
        String Response =
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<style>" +
                        "li {" +
                        "padding: 5px" +
                        "}" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<center>" +
                        "<h2>Credit added successfully!</h2>" +
                        "</center>" +
                        "<br>" +
                        "<center>" +
                        "<h2>Your credit is now: " + user.getCredit() + "</h2>" +
                        "</center>" +
                        "</body>" +
                        "</html>";
        return Response;
    }

    public String rate_commodity_page_handler(String Path){
        String[] Path_Parameters = Path.split("/");
        String username = Path_Parameters[2].substring(3, Path_Parameters[2].length() - 3);
        int commodity_id = Integer.parseInt(Path_Parameters[3].substring(3, Path_Parameters[3].length() - 3));
        int given_rate = Integer.parseInt(Path_Parameters[4].substring(3, Path_Parameters[4].length() - 3));
        this.rate_commodity(username, commodity_id, given_rate);
        String Response =
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<style>" +
                        "li {" +
                        "padding: 5px" +
                        "}" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<center>" +
                        "<h2>Commodity rated successfully!</h2>" +
                        "</center>" +
                        "<br>" +
                        "<center>" +
                        "<h2>Your rate to this commodity is now: " + given_rate + "</h2>" +
                        "</center>" +
                        "</body>" +
                        "</html>";
        return Response;
    }

    public String rate_comment_page_handler(String Path){
        String[] Path_Parameters = Path.split("/");
        String username = Path_Parameters[2].substring(3, Path_Parameters[2].length() - 3);
        int comment_id = Integer.parseInt(Path_Parameters[3].substring(3, Path_Parameters[3].length() - 3));
        int given_rate = Integer.parseInt(Path_Parameters[4].substring(3, Path_Parameters[4].length() - 3));
        this.rate_comment(username, comment_id, given_rate);
        String Response =
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<style>" +
                        "li {" +
                        "padding: 5px" +
                        "}" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<center>" +
                        "<h2>Comment rated successfully!</h2>" +
                        "</center>" +
                        "<br>" +
                        "<center>" +
                        "<h2>Your rate to this comment is now: " + given_rate + "</h2>" +
                        "</center>" +
                        "</body>" +
                        "</html>";
        return Response;
    }

    public String add_to_buy_list_page_handler(String Path){
        String[] Path_Parameters = Path.split("/");
        String username = Path_Parameters[2].substring(3, Path_Parameters[2].length() - 3);
        int commodity_id = Integer.parseInt(Path_Parameters[3].substring(3, Path_Parameters[3].length() - 3));
        this.add_to_buy_list(username, commodity_id);
        String Response =
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<style>" +
                        "li {" +
                        "padding: 5px" +
                        "}" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<center>" +
                        "<h2>Commodity added to Buy List successfully!</h2>" +
                        "</center>" +
                        "</body>" +
                        "</html>";
        return Response;
    }

    public String remove_from_buy_list_page_handler(String Path){
        String[] Path_Parameters = Path.split("/");
        String username = Path_Parameters[2].substring(3, Path_Parameters[2].length() - 3);
        int commodity_id = Integer.parseInt(Path_Parameters[3].substring(3, Path_Parameters[3].length() - 3));
        this.remove_from_buy_list(username, commodity_id);
        String Response =
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<style>" +
                        "li {" +
                        "padding: 5px" +
                        "}" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<center>" +
                        "<h2>Commodity removed from Buy List successfully!</h2>" +
                        "</center>" +
                        "</body>" +
                        "</html>";
        return Response;
    }

    public String purchase_items_in_buy_list_page_handler(String Path_Param){
        String username = Path_Param.substring(1, Path_Param.length() - 1);
        this.add_to_purchased_list(username, 0);
        String Response =
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<style>" +
                        "li {" +
                        "padding: 5px" +
                        "}" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<center>" +
                        "<h2>Commodities in Buy list purchased successfully!</h2>" +
                        "</center>" +
                        "</body>" +
                        "</html>";
        return Response;
    }

    public String show_provider_page_handler(int provider_id){
        Provider provider = get_Provider(provider_id);
        String Response =
                        "<ul>" +
                        "<li id=\"id\">Id: " + Integer.toString(provider.get_id()) + "</li>" +
                        "<li id=\"name\">Name: " + provider.get_name() + "</li>" +
                        "<li id=\"registryDate\">Registry Date: " + provider.get_registryDate() + "</li>" +
                        "</ul>" +
                        "<table>" +
                        "<caption><h3>Provided Commodities</h3></caption>" +
                        "<tr>" +
                        "<th>Id</th>" +
                        "<th>Name</th>" +
                        "<th>Price</th>" +
                        "<th>Categories</th>" +
                        "<th>Rating</th>" +
                        "<th>In Stock</th>" +
                        "</tr>";
        for(int i = 0; i < Commodities.size(); i++)
            if(Commodities.get(i).get_provider_id() == provider_id) {
                Response +=
                        "<tr>" +
                                "<th>" + Integer.toString(Commodities.get(i).get_id()) + "</th>" +
                                "<th>" + Commodities.get(i).get_name() + "</th>" +
                                "<th>" + Integer.toString(Commodities.get(i).get_price()) + "</th>" +
                                "<th>" + Commodities.get(i).get_categories() + "</th>" +
                                "<th>" + Float.toString(Commodities.get(i).get_rating()) + "</th>" +
                                "<th>" + Integer.toString(Commodities.get(i).get_inStock()) + "</th>" +
                                "<td><a href=\"" + "/Baloot_ShoppingCenter_Phase2_war_exploded/commodities/{" + Commodities.get(i).get_id() + "}\"" + ">Link</a></td>" +
                                "</tr>";
            }
        Response +=
                "</table>" +
                        "</body>" +
                        "</html>";
        return Response;
    }

    public String show_commodity_page_handler(String Path_Parameter){
        int commodity_id = Integer.parseInt(Path_Parameter);
        Commodity commodity = get_Commodity(commodity_id);
        String Response =
                        "<ul>" +
                        "<li id=\"id\">Id: " + Integer.toString(commodity.get_id()) + "</li>" +
                        "<li id=\"name\">Name: " + commodity.get_name() + "</li>" +
                        "<li id=\"providerId\">Provider Id: " + Integer.toString(commodity.get_provider_id()) + "</li>" +
                        "<li id=\"price\">Price: " + Integer.toString(commodity.get_price()) + "</li>" +
                        "<li id=\"categories\">Categories: " + commodity.get_categories() + "</li>" +
                        "<li id=\"rating\">Rating: " + Float.toString(commodity.get_rating()) + "</li>" +
                        "<li id=\"inStock\">In Stock: " + Integer.toString(commodity.get_inStock()) + "</li>" +
                        "</ul>" +
                        "<label>Add Your Comment:</label>" +
                        "<form action=\"/Baloot_ShoppingCenter_Phase2_war_exploded/commodity\" method=\"GET\">" +
                        "   <input type=\"text\" name=\"comment\" value=\"\" />" +
                        "   <input " + "id=\"add_comment\" " + "type=\"hidden\" " + "name=\"action\" " + "value=\"comment\"" + "/>" +
                        "   <input " + "id=\"commodity_id\" " + "type=\"hidden\" " + "name=\"commodity_id\" " + "value=\"" + Integer.toString(commodity_id) + "\"" + "/>";
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                        String current_date = formatter.format(date);
                        Response +=
                        "   <input " + "id=\"comment_date\" " + "type=\"hidden\" " + "name=\"comment_date\" " + "value=\"" + current_date + "\"/>" +
                        "   <button type=\"submit\">submit</button>" +
                        "</form>" +
                        "<br>" +
                        "<form action=\"/Baloot_ShoppingCenter_Phase2_war_exploded/commodity\" method=\"GET\">" +
                        "<label>Rate(between 1 and 10):</label>" +
                        "   <input type=\"number\" id=\"quantity\" name=\"rate\" min=\"1\" max=\"10\">" +
                        "   <input " + "id=\"commodity_rate\" " + "type=\"hidden\" " + "name=\"action\" " + "value=\"rate\"" + "/>" +
                        "   <input " + "id=\"commodity_id\" " + "type=\"hidden\" " + "name=\"commodity_id\" " + "value=\"" + Integer.toString(commodity_id) + "\"" + "/>" +
                        "   <button type=\"submit\">Rate</button>" +
                        "</form>" +
                        "<br>" +
                        "<form action=\"/Baloot_ShoppingCenter_Phase2_war_exploded/commodity\" method=\"GET\">" +
                        "   <input " + "id=\"add_to_buy_list\" " + "type=\"hidden\" " + "name=\"action\" " + "value=\"add\"" + "/>" +
                        "   <input " + "id=\"commodity_id\" " + "type=\"hidden\" " + "name=\"commodity_id\" " + "value=\"" + Integer.toString(commodity_id) + "\"" + "/>" +
                        "<button type=\"submit\">Add to BuyList</button>" +
                        "</form>" +
                        "<br/>" +
                        "<table>" +
                        "<caption><h2>Comments</h2></caption>" +
                        "<tr>" +
                        "<th>username</th>" +
                        "<th>comment</th>" +
                        "<th>date</th>" +
                        "<th>likes</th>" +
                        "<th>dislikes</th>" +
                        "</tr>";
        for(int i = 0; i < Users_Comments.size(); i++){
            if(Users_Comments.get(i).get_commodity_id() == commodity_id){
                Response += "<tr>" +
                        "<td>" + Users_Comments.get(i).get_user_email() + "</td>" +
                        "<td>" + Users_Comments.get(i).get_comment() + "</td>" +
                        "<td>" + Users_Comments.get(i).get_comment_date() + "</td>" +
                        "<td>" +
                        "<form action=\"/Baloot_ShoppingCenter_Phase2_war_exploded/commodity\" method=\"GET\">" +
                        "<label for=\"\">" + Users_Comments.get(i).get_number_of_likes() + " </label>" +
                        "<input " +
                        "id=\"comment_rate\" " +
                        "type=\"hidden\" " +
                        "name=\"action\" " +
                        "value=\"like\"" +
                        "/>" +
                        "<input " +
                        "id=\"comment_id\" " +
                        "type=\"hidden\" " +
                        "name=\"comment_id\" " +
                        "value=\"" + Integer.toString(i) + "\"" +
                        "/>" +
                        "<input " +
                        "id=\"commodity_id\" " +
                        "type=\"hidden\" " +
                        "name=\"commodity_id\" " +
                        "value=\"" + commodity_id + "\"" +
                        "/>" +
                        "<button type=\"submit\">like</button>" +
                        "</form>" +
                        "</td>" +
                        "<td>" +
                        "<form action=\"/Baloot_ShoppingCenter_Phase2_war_exploded/commodity\" " + "method=\"GET\">" +
                        "<label for=\"\">" + Users_Comments.get(i).get_number_of_dislikes() + " </label>" +
                        "<input " +
                        "id=\"comment_rate\" " +
                        "type=\"hidden\" " +
                        "name=\"action\" " +
                        "value=\"dislike\"" +
                        "/>" +
                        "<input " +
                        "id=\"comment_id\" " +
                        "type=\"hidden\" " +
                        "name=\"comment_id\" " +
                        "value=\"" + Integer.toString(i) + "\"" +
                        "/>" +
                        "<input " +
                        "id=\"commodity_id\" " +
                        "type=\"hidden\" " +
                        "name=\"commodity_id\" " +
                        "value=\"" + commodity_id + "\"" +
                        "/>" +
                        "<button type=\"submit\">dislike</button>" +
                        "</form>" +
                        "</td>" +
                        "</tr>";
            }
        }
        Response += "</table>" +
                 "<br><br>";
        List<Float> Commodities_similarity_scores = new ArrayList<>();
        List<Integer> Commodities_similarity_scores_indices = new ArrayList<>();
        for(int index_of_commodity = 0; index_of_commodity < Commodities.size(); index_of_commodity++)
            if(Commodities.get(index_of_commodity).get_id() != commodity_id){
                if(Commodities.get(index_of_commodity).get_categories().equals(commodity.get_categories()))
                    Commodities_similarity_scores.add((11 + Commodities.get(index_of_commodity).get_rating()));
                else
                    Commodities_similarity_scores.add((Commodities.get(index_of_commodity).get_rating()));
                Commodities_similarity_scores_indices.add(Commodities.get(index_of_commodity).get_id());
            }
        for(int i = 0; i < 5; i++)
            for(int j = Commodities_similarity_scores.size() - 2; j >= 0; j--)
                if(Commodities_similarity_scores.get(j + 1) > Commodities_similarity_scores.get(j)) {
                    Float temp = Commodities_similarity_scores.get(j + 1);
                    Commodities_similarity_scores.set(j + 1, Commodities_similarity_scores.get(j));
                    Commodities_similarity_scores.set(j, temp);
                    int temp_index = Commodities_similarity_scores_indices.get(j + 1);
                    Commodities_similarity_scores_indices.set(j + 1, Commodities_similarity_scores_indices.get(j));
                    Commodities_similarity_scores_indices.set(j, temp_index);
                }
        Response += "<table>" +
                        "<caption><h2>Suggested Commodities</h2></caption>";
        for(int i = 0; i < 5; i++) {
            Commodity most_5_similar_commodities = get_Commodity(Commodities_similarity_scores_indices.get(i));
            Response +=
                    "<tr>" +
                            "<th>" + Integer.toString(most_5_similar_commodities.get_id()) + "</th>" +
                            "<th>" + most_5_similar_commodities.get_name() + "</th>" +
                            "<th>" + Integer.toString(most_5_similar_commodities.get_price()) + "</th>" +
                            "<th>" + most_5_similar_commodities.get_categories() + "</th>" +
                            "<th>" + Float.toString(most_5_similar_commodities.get_rating()) + "</th>" +
                            "<th>" + Integer.toString(most_5_similar_commodities.get_inStock()) + "</th>" +
                            "<td><a href = \"/Baloot_ShoppingCenter_Phase2_war_exploded/commodities/{" + Integer.toString(most_5_similar_commodities.get_id()) + "}\"> Link </a ></td>" +
                            "</tr>";
        }
        Response += "</table>" +
                "</body>" +
                "</html>";
        return Response;
    }

    public String show_commodity_by_range_of_price_page_handler(String Path){
        String[] Path_Parameters = Path.split("/");
        int start_price = Integer.parseInt(Path_Parameters[3].substring(3, Path_Parameters[3].length() - 3));
        int end_price = Integer.parseInt(Path_Parameters[4].substring(3, Path_Parameters[4].length() - 3));
        String Response =
                "<!DOCTYPE html>" +
                        "<html lang=/" + "en/" + ">" +
                        "<head>" +
                        "<meta charset=/" + "UTF-8/" + ">" +
                        "<title>Commodities</title>" +
                        "<style>" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<table>" +
                        "<tr>" +
                        "<th>Id</th>" +
                        "<th>Name</th>" +
                        "<th>Provider Id</th>" +
                        "<th>Price</th>" +
                        "<th>Categories</th>" +
                        "<th>Rating</th>" +
                        "<th>In Stock</th>" +
                        "<th>Links</th>" +
                        "</tr>";
        for(int i = 0; i < Commodities.size(); i++){
            if(start_price < Commodities.get(i).get_price() && Commodities.get(i).get_price() < end_price) {
                Response += "<tr>" +
                        "<td>" + Commodities.get(i).get_id() + "</td>" +
                        "<td>" + Commodities.get(i).get_name() + "</td>" +
                        "<td>" + Commodities.get(i).get_provider_id() + "</td>" +
                        "<td>" + Commodities.get(i).get_price() + "</td>" +
                        "<td>" + Commodities.get(i).get_categories() + "</td>" +
                        "<td>" + Commodities.get(i).get_rating() + "</td>" +
                        "<td>" + Commodities.get(i).get_inStock() + "</td>" +
                        "<td><a href=\"" + "/commodities/{" + Commodities.get(i).get_id() + "}\"" + ">Link</a></td>" +
                        "</tr>";
            }
        }
        Response +=
                "</table>" +
                        "</body>" +
                        "</html>";
        return Response;
    }

    public String show_sorted_commodities_by_price_page_handler(){
        String Response =
                "<style>" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<table>" +
                        "<tr>" +
                        "<th>Id</th>" +
                        "<th>Name</th>" +
                        "<th>Provider Id</th>" +
                        "<th>Price</th>" +
                        "<th>Categories</th>" +
                        "<th>Rating</th>" +
                        "<th>In Stock</th>" +
                        "<th>Links</th>" +
                        "</tr>";
        List<Integer> Commodities_prices = new ArrayList<>();
        for(int index_of_commodity = 0; index_of_commodity < Commodities.size(); index_of_commodity++)
            Commodities_prices.add(Commodities.get(index_of_commodity).get_price());
        Collections.sort(Commodities_prices);
        for(int i = 0; i < Commodities_prices.size(); i++)
            for(int j = 0; j < Commodities.size(); j++){
                if( Commodities.get(j).get_price() == Commodities_prices.get(i)) {
                    Response += "<tr>" +
                            "<td>" + Commodities.get(j).get_id() + "</td>" +
                            "<td>" + Commodities.get(j).get_name() + "</td>" +
                            "<td>" + Commodities.get(j).get_provider_id() + "</td>" +
                            "<td>" + Commodities.get(j).get_price() + "</td>" +
                            "<td>" + Commodities.get(j).get_categories() + "</td>" +
                            "<td>" + Commodities.get(j).get_rating() + "</td>" +
                            "<td>" + Commodities.get(j).get_inStock() + "</td>" +
                            "<td><a href=\"" + "/Baloot_ShoppingCenter_Phase2_war_exploded/commodities/{" + Commodities.get(j).get_id() + "}\"" + ">Link</a></td>" +
                            "</tr>";
                }
            }
        Response += "</table>";
        return Response;
    }

    public String show_commodity_by_category_page_handler(String Path_Parameter){
        List<String> categories = new ArrayList<String>(Arrays.asList(Path_Parameter.substring(1, Path_Parameter.length() - 1).split(", ")));
        String Response =
                        "<style>" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<table>" +
                        "<tr>" +
                        "<th>Id</th>" +
                        "<th>Name</th>" +
                        "<th>Provider Id</th>" +
                        "<th>Price</th>" +
                        "<th>Categories</th>" +
                        "<th>Rating</th>" +
                        "<th>In Stock</th>" +
                        "<th>Links</th>" +
                        "</tr>";
        for(int i = 0; i < Commodities.size(); i++){
            if(Commodities.get(i).get_categories().equals(categories)) {
                Response += "<tr>" +
                        "<td>" + Commodities.get(i).get_id() + "</td>" +
                        "<td>" + Commodities.get(i).get_name() + "</td>" +
                        "<td>" + Commodities.get(i).get_provider_id() + "</td>" +
                        "<td>" + Commodities.get(i).get_price() + "</td>" +
                        "<td>" + Commodities.get(i).get_categories() + "</td>" +
                        "<td>" + Commodities.get(i).get_rating() + "</td>" +
                        "<td>" + Commodities.get(i).get_inStock() + "</td>" +
                        "<td><a href=\"" + "/Baloot_ShoppingCenter_Phase2_war_exploded/commodities/{" + Commodities.get(i).get_id() + "}\"" + ">Link</a></td>" +
                        "</tr>";
            }
        }
        Response += "</table>";
        return Response;
    }

    public String show_commodity_by_name_page_handler(String Path_Parameter){
        String given_name = Path_Parameter;
        String Response =
                "<style>" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<table>" +
                        "<tr>" +
                        "<th>Id</th>" +
                        "<th>Name</th>" +
                        "<th>Provider Id</th>" +
                        "<th>Price</th>" +
                        "<th>Categories</th>" +
                        "<th>Rating</th>" +
                        "<th>In Stock</th>" +
                        "<th>Links</th>" +
                        "</tr>";
        for(int i = 0; i < Commodities.size(); i++){
            if(Commodities.get(i).get_name().contains(given_name)) {
                Response += "<tr>" +
                        "<td>" + Commodities.get(i).get_id() + "</td>" +
                        "<td>" + Commodities.get(i).get_name() + "</td>" +
                        "<td>" + Commodities.get(i).get_provider_id() + "</td>" +
                        "<td>" + Commodities.get(i).get_price() + "</td>" +
                        "<td>" + Commodities.get(i).get_categories() + "</td>" +
                        "<td>" + Commodities.get(i).get_rating() + "</td>" +
                        "<td>" + Commodities.get(i).get_inStock() + "</td>" +
                        "<td><a href=\"" + "/Baloot_ShoppingCenter_Phase2_war_exploded/commodities/{" + Commodities.get(i).get_id() + "}\"" + ">Link</a></td>" +
                        "</tr>";
            }
        }
        Response += "</table>";
        return Response;
    }

    public String show_all_commodities_page_handler(){
        String Response =
                        "<style>" +
                        "table{" +
                        "width: 100%;" +
                        "text-align: center;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<table>" +
                        "<tr>" +
                        "<th>Id</th>" +
                        "<th>Name</th>" +
                        "<th>Provider Id</th>" +
                        "<th>Price</th>" +
                        "<th>Categories</th>" +
                        "<th>Rating</th>" +
                        "<th>In Stock</th>" +
                        "<th>Links</th>" +
                        "</tr>";
        for(int i = 0; i < Commodities.size(); i++){
            Response +=  "<tr>" +
                    "<td>" + Commodities.get(i).get_id() + "</td>" +
                    "<td>" + Commodities.get(i).get_name() + "</td>" +
                    "<td>" + Commodities.get(i).get_provider_id() + "</td>" +
                    "<td>" + Commodities.get(i).get_price() + "</td>" +
                    "<td>" + Commodities.get(i).get_categories() + "</td>" +
                    "<td>" + Commodities.get(i).get_rating() + "</td>" +
                    "<td>" + Commodities.get(i).get_inStock() + "</td>" +
                    "<td><a href=\"" + "/Baloot_ShoppingCenter_Phase2_war_exploded/commodities/{" + Commodities.get(i).get_id() + "}\"" + ">Link</a></td>" +
                    "</tr>";
        }
        Response += "</table>";
        return Response;
    }

    public String redirect_to_addCredit_page(String Path){
        String username = Path.substring(Path.indexOf('(') + 1,Path.indexOf(')'));
        String Path_Parameters = Path.substring(Path.indexOf('?') + 1, Path.length());
        String amount_of_credit_to_add = Path_Parameters.substring(7, Path_Parameters.length());
        String Response = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/addCredit/{" + username + "}/{" + amount_of_credit_to_add + "}\"/>" +
                "</head>" +
                "</html>";
        return Response;
    }

    public String redirect_to_rateCommodity_page(String Path){
        String commodity_id = Path.substring(Path.indexOf('(') + 1,Path.indexOf(')'));
        String Path_Parameters = Path.substring(Path.indexOf('?') + 1, Path.length());
        String username = Path_Parameters.substring(9, Path_Parameters.indexOf('&'));
        String rate = Path_Parameters.substring(Path_Parameters.indexOf('&') + 6, Path_Parameters.length());
        String Response = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/rateCommodity/{" + username + "}/{" + commodity_id + "}/{" + rate + "}\"/>" +
                "</head>" +
                "</html>";
        return Response;
    }

    public String redirect_to_voteComment_page(String Path){
        String comment_id = Path.substring(Path.indexOf('(') + 1,Path.indexOf(')'));
        String Path_Parameters = Path.substring(Path.indexOf('?') + 1, Path.length());
        String username = Path_Parameters.substring(9, Path_Parameters.indexOf('&'));
        String rate = Path_Parameters.substring(Path_Parameters.indexOf('&') + 6, Path_Parameters.length());
        String Response = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/voteComment/{" + username + "}/{" + comment_id + "}/{" + rate + "}\"/>" +
                "</head>" +
                "</html>";
        return Response;
    }

    public String redirect_to_addToBuyList_page(String Path){
        String commodity_id = Path.substring(Path.indexOf('(') + 1,Path.indexOf(')'));
        String Path_Parameters = Path.substring(Path.indexOf('?') + 1, Path.length());
        String username = Path_Parameters.substring(9, Path_Parameters.length());
        String Response = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/addToBuyList/{" + username + "}/{" + commodity_id + "}\"/>" +
                "</head>" +
                "</html>";
        return Response;
    }

    public String redirect_to_removeFromBuyList_page(String Path){
        String username = Path.substring(Path.indexOf('(') + 1,Path.indexOf(')'));
        String Path_Parameters = Path.substring(Path.indexOf('?') + 1, Path.length());
        String commodity_id = Path_Parameters.substring(12, Path_Parameters.length());
        String Response = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/removeFromBuyList/{" + username + "}/{" + commodity_id + "}\"/>" +
                "</head>" +
                "</html>";
        return Response;
    }
}
