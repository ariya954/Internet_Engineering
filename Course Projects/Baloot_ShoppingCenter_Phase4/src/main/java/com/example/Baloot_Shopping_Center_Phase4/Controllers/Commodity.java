package com.example.Baloot_Shopping_Center_Phase4.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase4.FronEnd_Pages_Handlers.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class Commodity {
    @RequestMapping(value = "/commodity")
    public String Commodity_Page_Response(@RequestParam(value = "id", required = false, defaultValue = "1") int commodity_id, @RequestParam(value = "username") String current_logged_in_username, @RequestParam(value = "add_to_cart", required = false, defaultValue = "0") boolean add_to_cart, @RequestParam(value = "remove_from_cart", required = false, defaultValue = "0") boolean remove_from_cart, @RequestParam(value = "comment", required = false, defaultValue = "") String comment, @RequestParam(value = "comment_id", required = false, defaultValue = "0") int comment_id, @RequestParam(value = "comment_rate", required = false, defaultValue = "0") int comment_rate, @RequestParam(value = "commodity_rate", required = false, defaultValue = "0") int commodity_rate) throws IOException, SQLException {
        Commodity_Page_Handler page_handler = new Commodity_Page_Handler();
        return page_handler.Response(commodity_id, current_logged_in_username, add_to_cart, remove_from_cart, comment, comment_id, comment_rate, commodity_rate);
    }
}