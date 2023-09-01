package com.example.Baloot_Shopping_Center_Phase3.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase3.FronEnd_Pages_Handlers.*;

import java.io.IOException;

@RestController
public class Commodities {
    @RequestMapping(value = "/commodities")
    public String Commodities_Page_Response(@RequestParam(value = "page", required = false, defaultValue = "1") int number_of_current_page, @RequestParam(value = "username") String current_logged_in_username, @RequestParam(value = "sort_by_price", required = false, defaultValue = "0") boolean sort_by_price,  @RequestParam(value = "sort_by_name", required = false, defaultValue = "0") boolean sort_by_name, @RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "category", required = false, defaultValue = "") String category) throws IOException {
        Commodities_Page_Handler page_handler = new Commodities_Page_Handler();
        return page_handler.Response(number_of_current_page, current_logged_in_username, sort_by_price, sort_by_name, name, category);
    }
}