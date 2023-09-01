package com.example.Baloot_Shopping_Center_Phase3.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase3.FronEnd_Pages_Handlers.*;

import java.io.IOException;

@RestController
public class User {
    @RequestMapping(value = "/user")
    public String User_Page_Response(@RequestParam(value = "username") String username, @RequestParam(value = "add_credit", required = false, defaultValue = "0") int credit_to_added, @RequestParam(value = "pay", required = false, defaultValue = "0") boolean pay) throws IOException {
        User_Page_Handler page_handler = new User_Page_Handler();
        return page_handler.Response(username, credit_to_added, pay);
    }
}