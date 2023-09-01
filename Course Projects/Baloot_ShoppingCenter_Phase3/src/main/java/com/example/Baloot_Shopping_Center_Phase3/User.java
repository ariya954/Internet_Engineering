package com.example.Baloot_Shopping_Center_Phase3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private String birthDate;
    private String address;
    private int credit;

    private List<Buy_List> User_Buy_List = new ArrayList<>();

    private List<Purchased_List> User_Purchased_List = new ArrayList<>();

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

    public int get_number_of_all_commodities_in_cart() {
        int number_of_all_commodities_in_cart = 0;
        for(int i = 0; i < User_Buy_List.size(); i++)
            number_of_all_commodities_in_cart += User_Buy_List.get(i).in_cart;
        return number_of_all_commodities_in_cart;
    }

    public int get_number_of_commodity_in_cart(int commodity_id) {
        for(int i = 0; i < User_Buy_List.size(); i++)
            if(User_Buy_List.get(i).Commodity == commodity_id)
                return User_Buy_List.get(i).in_cart;
        return 0; //this commodity not found in user cart
    }


    public List<Buy_List> get_User_Buy_List(){ return User_Buy_List; }

    public List<Purchased_List> get_User_Purchased_List(){ return User_Purchased_List; }

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

    public void add_commodity_to_user_buy_list(int Commodity_id){
        for (int index_of_buy_list_item = 0; index_of_buy_list_item < User_Buy_List.size(); index_of_buy_list_item++)
            if(User_Buy_List.get(index_of_buy_list_item).Commodity == Commodity_id) {
                User_Buy_List.get(index_of_buy_list_item).in_cart++;
                return;
            }
        Buy_List New_Buy_List = new Buy_List(Commodity_id);
        User_Buy_List.add(New_Buy_List);
    }

    public void add_commodity_to_user_purchased_list(int Commodity_id, int number_of_bought_commodity){
        User_Purchased_List.add(new Purchased_List(Commodity_id, number_of_bought_commodity));
    }

    public void remove_commodity_from_user_buy_list(int Commodity_id){
        for (int index_of_User_Buy_List = 0; index_of_User_Buy_List < User_Buy_List.size(); index_of_User_Buy_List++)
            if (User_Buy_List.get(index_of_User_Buy_List).Commodity == Commodity_id) {
                if(User_Buy_List.get(index_of_User_Buy_List).in_cart > 1)
                    User_Buy_List.get(index_of_User_Buy_List).in_cart--;
                else
                    User_Buy_List.remove(User_Buy_List.get(index_of_User_Buy_List));
                return;
            }
        System.out.println("Commodity isn't available in your buy list");
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