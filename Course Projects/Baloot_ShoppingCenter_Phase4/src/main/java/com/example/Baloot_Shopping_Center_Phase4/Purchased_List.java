package com.example.Baloot_Shopping_Center_Phase4;

public class Purchased_List {
    public String buyer;
    public int number_of_purchased;
    public int Commodity;
    public Purchased_List(int Commodity_id, int number_of_purchased_commodity, String buyer_name) {
        Commodity = Commodity_id;
        number_of_purchased = number_of_purchased_commodity;
        buyer = buyer_name;
    }
}