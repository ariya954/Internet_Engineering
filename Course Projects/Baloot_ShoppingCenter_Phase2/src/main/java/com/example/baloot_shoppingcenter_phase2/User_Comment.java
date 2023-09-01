package com.example.baloot_shoppingcenter_phase2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class User_Comment {
    private String comment;
    private int commodity_id;
    private String user_email;
    private String comment_date;
    private List<User_Rate> Users_Rates = new ArrayList<>();
    private float rate;

    @JsonCreator
    User_Comment(@JsonProperty("userEmail")String given_user_email, @JsonProperty("commodityId")int given_commodity_id, @JsonProperty("text")String given_comment, @JsonProperty("date")String given_comment_date) {
        comment = given_comment;
        user_email = given_user_email;
        commodity_id = given_commodity_id;
        comment_date = given_comment_date;
    }

    public String get_comment() { return comment; }
    public String get_user_email() { return user_email; }
    public int get_commodity_id() { return commodity_id; }
    public String get_comment_date() { return comment_date; }
    public float get_rate() { return rate; }
    public int get_number_of_likes(){
        int number_of_likes = 0;
        for (int index_of_User_Rate = 0; index_of_User_Rate < Users_Rates.size(); index_of_User_Rate++)
            if(Users_Rates.get(index_of_User_Rate).rate == 1)
                number_of_likes++;
        return number_of_likes;
    }
    public int get_number_of_dislikes(){
        int number_of_dislikes = 0;
        for (int index_of_User_Rate = 0; index_of_User_Rate < Users_Rates.size(); index_of_User_Rate++)
            if(Users_Rates.get(index_of_User_Rate).rate == -1)
                number_of_dislikes++;
        return number_of_dislikes;
    }

    public void change_comment(String new_comment) { comment = new_comment; }

    public void add_user_rate(String username, int user_rate){
        if(user_rate < -1 || user_rate > 1)
            System.out.println("Rate must be an Integer between -1 to 1");
        else {
            for (int index_of_User_Rate = 0; index_of_User_Rate < Users_Rates.size(); index_of_User_Rate++)
                if (Users_Rates.get(index_of_User_Rate).username.equals(username)) {
                    Users_Rates.get(index_of_User_Rate).rate = user_rate;
                    rate = get_mean_of_users_rates();
                    return;
                }
            User_Rate New_User_Rate = new User_Rate(username, user_rate);
            Users_Rates.add(New_User_Rate);
            rate = get_mean_of_users_rates();
        }
    }

    public float get_mean_of_users_rates(){
        float sum_of_user_rates = 0;
        for (int index_of_User_Rate = 0; index_of_User_Rate < Users_Rates.size(); index_of_User_Rate++)
            sum_of_user_rates += Users_Rates.get(index_of_User_Rate).rate;
        return sum_of_user_rates / Users_Rates.size();
    }
}
