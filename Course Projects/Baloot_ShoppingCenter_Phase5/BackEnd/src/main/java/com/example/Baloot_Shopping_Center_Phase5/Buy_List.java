package com.example.Baloot_Shopping_Center_Phase5;

public class Buy_List {
    public String owner;
    public String name;
    public int price;
    public int in_cart = 1;
    public int Commodity;
    public Buy_List(int Commodity_id) {
        Commodity = Commodity_id;
    }
}