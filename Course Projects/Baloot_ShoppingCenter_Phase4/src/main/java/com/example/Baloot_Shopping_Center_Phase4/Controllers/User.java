package com.example.Baloot_Shopping_Center_Phase4.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase4.FronEnd_Pages_Handlers.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class User {
    @RequestMapping(value = "/user")
    public String User_Page_Response(@RequestParam(value = "username") String username, @RequestParam(value = "add_credit", required = false, defaultValue = "0") int credit_to_added, @RequestParam(value = "pay", required = false, defaultValue = "0") boolean pay, @RequestParam(value = "commodity_id", required = false, defaultValue = "1") int commodity_id, @RequestParam(value = "add_to_cart", required = false, defaultValue = "0") boolean add_to_cart, @RequestParam(value = "remove_from_cart", required = false, defaultValue = "0") boolean remove_from_cart) throws IOException, SQLException {
        User_Page_Handler page_handler = new User_Page_Handler();
        return page_handler.Response(username, credit_to_added, pay, commodity_id, add_to_cart, remove_from_cart);
    }
}