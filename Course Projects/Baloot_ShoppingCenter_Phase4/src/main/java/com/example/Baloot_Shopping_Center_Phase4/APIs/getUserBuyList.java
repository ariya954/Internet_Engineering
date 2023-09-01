package com.example.Baloot_Shopping_Center_Phase4.APIs;

import com.example.Baloot_Shopping_Center_Phase4.Buy_List;
import com.example.Baloot_Shopping_Center_Phase4.Commodity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase4.Shopping_WebSite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class getUserBuyList {
    @RequestMapping(value = "/getUserBuyList")
    public List<Buy_List> getUserBuyList_Response(@RequestParam(value = "username") String username) throws IOException, SQLException {
        List<Buy_List> user_buy_lists = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(username).get_User_Buy_List();
        for(int i = 0; i < user_buy_lists.size(); i++){
            Commodity buy_list_item_commodity = Shopping_WebSite.get_Shopping_WebSite_instance().get_Commodity(user_buy_lists.get(i).Commodity);
            user_buy_lists.get(i).name = buy_list_item_commodity.get_name();
            user_buy_lists.get(i).price = buy_list_item_commodity.get_price();
        }
        return user_buy_lists;
    }
}