package com.example.Baloot_Shopping_Center_Phase5.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase5.FronEnd_Pages_Handlers.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class Provider {
    @RequestMapping(value = "/provider")
    public String Provider_Page_Response(@RequestParam(value = "id", required = false, defaultValue = "1") int provider_id, @RequestParam(value = "username") String current_logged_in_username) throws IOException, SQLException {
        Provider_Page_Handler page_handler = new Provider_Page_Handler();
        return page_handler.Response(provider_id, current_logged_in_username);
    }
}