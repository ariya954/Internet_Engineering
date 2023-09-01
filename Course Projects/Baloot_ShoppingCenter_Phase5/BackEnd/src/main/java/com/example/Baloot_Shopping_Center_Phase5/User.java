package com.example.Baloot_Shopping_Center_Phase5;

import com.example.Baloot_Shopping_Center_Phase5.DataBase.BuyList_DataBase;
import com.example.Baloot_Shopping_Center_Phase5.DataBase.PurchasedList_DataBase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.SQLException;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private String birthDate;
    private String address;
    private int credit;

    public User(){
        super();
    }
    @JsonCreator
    public User(@JsonProperty("username")String given_username, @JsonProperty("password")String given_password, @JsonProperty("email")String given_email, @JsonProperty("birthDate")String given_birthDate, @JsonProperty("address")String given_address, @JsonProperty("credit")int given_credit){
        username = given_username;
        password = given_password;
        email = given_email;
        birthDate = given_birthDate;
        address = given_address;
        credit = given_credit;
    }

    public String get_username(){ return username; }
    public String get_password(){ return password; }
    public String get_email(){ return email; }
    public String get_birthDate(){ return birthDate; }
    public String get_address(){ return address; }
    public int get_credit(){ return credit; }

    public int get_number_of_all_commodities_in_cart() throws SQLException {
        List<Buy_List> User_Buy_List = BuyList_DataBase.getInstance().FindByOwner(username);
        int number_of_all_commodities_in_cart = 0;
        for(int i = 0; i < User_Buy_List.size(); i++)
            number_of_all_commodities_in_cart += User_Buy_List.get(i).in_cart;
        return number_of_all_commodities_in_cart;
    }

    public int get_number_of_commodity_in_cart(int commodity_id) throws SQLException {
        Buy_List buy_list_of_given_commodity = BuyList_DataBase.getInstance().findByOwnerAndCommodity(username, commodity_id);
        if(buy_list_of_given_commodity != null)
            return BuyList_DataBase.getInstance().findByOwnerAndCommodity(username, commodity_id).in_cart;
        return 0;
    }

    public List<Buy_List> get_User_Buy_List() throws SQLException { return BuyList_DataBase.getInstance().FindByOwner(username); }

    public List<Purchased_List> get_User_Purchased_List() throws SQLException { return PurchasedList_DataBase.getInstance().FindByBuyer(username); }

    public void change_username(String new_username){
        username = new_username;
    }

    public void change_password(String new_password){
        password = new_password;
    }

    public void change_email(String new_email){
        email = new_email;
    }

    public void change_birthDate(String new_birthDate){
        birthDate = new_birthDate;
    }

    public void change_address(String new_address){
        address = new_address;
    }

    public void increase_credit(int given_amount){
        credit += given_amount;
    }

    public void decrease_credit(int given_amount){
        credit -= given_amount;
    }

    public void add_commodity_to_user_buy_list(int Commodity_id) throws SQLException {
        Buy_List buy_list_of_given_commodity = BuyList_DataBase.getInstance().findByOwnerAndCommodity(username, Commodity_id);
        if(buy_list_of_given_commodity != null) {
            buy_list_of_given_commodity.in_cart++;
            BuyList_DataBase.getInstance().updateIdByNewObject(username, buy_list_of_given_commodity);
        }
        else {
            Buy_List new_buy_list = new Buy_List(Commodity_id);
            new_buy_list.owner = username;
            BuyList_DataBase.getInstance().insert(new_buy_list);
        }
    }

    public void add_commodity_to_user_purchased_list(int Commodity_id, int number_of_bought_commodity) throws SQLException {
        PurchasedList_DataBase.getInstance().insert(new Purchased_List(Commodity_id, number_of_bought_commodity, username));
    }

    public void decrease_in_cart_of_buy_list(int Commodity_id) throws SQLException {
        Buy_List buy_list_of_given_commodity = BuyList_DataBase.getInstance().findByOwnerAndCommodity(username, Commodity_id);
        if(buy_list_of_given_commodity.in_cart > 1) {
            buy_list_of_given_commodity.in_cart--;
            BuyList_DataBase.getInstance().updateIdByNewObject(username, buy_list_of_given_commodity);
        }
        else
            BuyList_DataBase.getInstance().remove(buy_list_of_given_commodity);
    }

    public void remove_commodity_from_buy_list(int given_commodity_id) throws SQLException {
        Buy_List buy_list_of_given_commodity = BuyList_DataBase.getInstance().findByOwnerAndCommodity(username, given_commodity_id);
        BuyList_DataBase.getInstance().remove(buy_list_of_given_commodity);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public int getCredit() {
        return credit;
    }
}