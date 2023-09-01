package com.example.Baloot_Shopping_Center_Phase3.APIs;

import com.example.Baloot_Shopping_Center_Phase3.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase3.Shopping_WebSite;

import java.io.IOException;

@RestController
public class getUserInfo {
    @RequestMapping(value = "/getUserInfo")
    public User getCommodityInfo_Response(@RequestParam(value = "username") String username) throws IOException {
        return Shopping_WebSite.get_Shopping_WebSite_instance().get_User(username);
    }
}