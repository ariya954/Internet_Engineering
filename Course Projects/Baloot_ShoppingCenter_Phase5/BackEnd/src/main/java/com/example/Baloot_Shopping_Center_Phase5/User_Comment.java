package com.example.Baloot_Shopping_Center_Phase5;

import com.example.Baloot_Shopping_Center_Phase5.DataBase.CommentRate_DataBase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.SQLException;
import java.util.List;

public class User_Comment {
    private int comment_id;
    private String comment;
    private int commodity_id;
    private String user_email;
    private String comment_date;

    @JsonCreator
    public User_Comment(@JsonProperty("userEmail") String given_user_email, @JsonProperty("commodityId") int given_commodity_id, @JsonProperty("text") String given_comment, @JsonProperty("date") String given_comment_date) {
        comment = given_comment;
        user_email = given_user_email;
        commodity_id = given_commodity_id;
        comment_date = given_comment_date;
    }

    public int get_id() {
        return comment_id;
    }
    public String get_comment() { return comment; }
    public String get_user_email() { return user_email; }
    public int get_commodity_id() { return commodity_id; }
    public String get_comment_date() { return comment_date; }
    public int get_number_of_likes() throws SQLException {
        int number_of_likes = 0;
        List<User_Rate> Users_Rates = CommentRate_DataBase.getInstance().FindByComment(comment_id);
        for (int index_of_User_Rate = 0; index_of_User_Rate < Users_Rates.size(); index_of_User_Rate++)
            if(Users_Rates.get(index_of_User_Rate).rate == 1)
                number_of_likes++;
        return number_of_likes;
    }
    public int get_number_of_dislikes() throws SQLException {
        int number_of_dislikes = 0;
        List<User_Rate> Users_Rates = CommentRate_DataBase.getInstance().FindByComment(comment_id);
        for (int index_of_User_Rate = 0; index_of_User_Rate < Users_Rates.size(); index_of_User_Rate++)
            if(Users_Rates.get(index_of_User_Rate).rate == -1)
                number_of_dislikes++;
        return number_of_dislikes;
    }

    public void set_id(int id) {
        comment_id = id;
    }

    public void change_comment(String new_comment) { comment = new_comment; }

    public void add_user_rate(String username, int user_rate) throws SQLException {
        User_Rate rate = CommentRate_DataBase.getInstance().findByScorerAndComment(username, comment_id);
        if(rate != null) {
            User_Rate user_rate_to_be_updated = new User_Rate(username, comment_id, user_rate);
            CommentRate_DataBase.getInstance().updateIdByNewObject(username, user_rate_to_be_updated);
        }
        else
            CommentRate_DataBase.getInstance().insert(new User_Rate(username, comment_id, user_rate));
    }
}