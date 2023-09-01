package com.example.Baloot_Shopping_Center_Phase4;

import com.example.Baloot_Shopping_Center_Phase4.DataBase.CommodityRate_DataBase;
import com.example.Baloot_Shopping_Center_Phase4.DataBase.Commodity_DataBase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.SQLException;
import java.util.*;

public class Commodity {
    private int id;
    private String name;
    private String image;
    private int provider_id;
    private int price;
    private List<String> categories;
    private float rating;
    private int inStock;

    @JsonCreator
    public Commodity(@JsonProperty("id")int given_id, @JsonProperty("name")String given_name, @JsonProperty("image")String given_image, @JsonProperty("providerId")int given_provider_id, @JsonProperty("price")int given_price, @JsonProperty("categories")List<String> given_categories, @JsonProperty("rating")float given_rating, @JsonProperty("inStock")int given_inStock){
        id = given_id;
        name = given_name;
        image = given_image;
        provider_id = given_provider_id;
        price = given_price;
        categories = given_categories;
        rating = given_rating;
        inStock = given_inStock;
    }

    //without image for commodity
    public Commodity(@JsonProperty("id")int given_id, @JsonProperty("name")String given_name, @JsonProperty("providerId")int given_provider_id, @JsonProperty("price")int given_price, @JsonProperty("categories")List<String> given_categories, @JsonProperty("rating")float given_rating, @JsonProperty("inStock")int given_inStock){
        id = given_id;
        name = given_name;
        provider_id = given_provider_id;
        price = given_price;
        categories = given_categories;
        rating = given_rating;
        inStock = given_inStock;
    }

    public int get_id(){ return id; }
    public String get_name(){ return name; }
    public String get_image(){ return image; }
    public int get_provider_id(){ return provider_id; }
    public int get_price(){ return price; }
    public List<String> get_categories(){ return categories; }
    public float get_rating(){ return rating; }
    public int get_inStock(){ return inStock; }

    public void change_id(int new_id){ id = new_id; }

    public void change_name(String new_name){
        name = new_name;
    }

    public void change_provider_id(int new_provider_id){
        provider_id = new_provider_id;
    }

    public void change_price(int new_price){
        price = new_price;
    }

    public void change_categories(List<String> new_categories){
        categories = new_categories;
    }

    public void increase_inStock(int given_amount){
        inStock += given_amount;
    }

    public void decrease_inStock(int given_amount){
        inStock -= given_amount;
    }

    public void add_user_rate(String username, int user_rate) throws SQLException {
        User_Rate rate = CommodityRate_DataBase.getInstance().findByScorerAndCommodity(username, id);
        if(rate != null) {
            User_Rate user_rate_to_be_updated = new User_Rate(username, id, user_rate);
            CommodityRate_DataBase.getInstance().updateIdByNewObject(username, user_rate_to_be_updated);
        }
        else
            CommodityRate_DataBase.getInstance().insert(new User_Rate(username, id, user_rate));
        rating = get_mean_of_users_rates();
        Commodity_DataBase.getInstance().updateIdByNewObject(id, (Commodity) this);
    }

    public float get_mean_of_users_rates() throws SQLException {
        float sum_of_user_rates = 0;
        List<User_Rate> Users_Rates = CommodityRate_DataBase.getInstance().FindByCommodity(id);
        for (int index_of_User_Rate = 0; index_of_User_Rate < Users_Rates.size(); index_of_User_Rate++)
            sum_of_user_rates += Users_Rates.get(index_of_User_Rate).rate;
        return sum_of_user_rates / Users_Rates.size();
    }

    public int get_number_of_users_rates() throws SQLException {
        int number_of_users_rates = CommodityRate_DataBase.getInstance().FindByCommodity(id).size();
        return (number_of_users_rates > 0) ? number_of_users_rates : 1;
    }
}