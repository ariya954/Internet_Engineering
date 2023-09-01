package com.example.Baloot_Shopping_Center_Phase5;

public class User_Rate {
    public String username;
    public int rated_item_id;
    public int rate;
    public User_Rate(String given_username, int given_rated_item_id, int given_user_rate) {
        rate = given_user_rate;
        username = given_username;
        rated_item_id = given_rated_item_id;
    }
}