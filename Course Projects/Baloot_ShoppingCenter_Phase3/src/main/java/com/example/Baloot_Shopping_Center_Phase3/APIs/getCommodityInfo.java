package com.example.Baloot_Shopping_Center_Phase3.APIs;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase3.Commodity;
import com.example.Baloot_Shopping_Center_Phase3.Shopping_WebSite;

import java.io.IOException;

@RestController
public class getCommodityInfo {
    @RequestMapping(value = "/getCommodityInfo")
    @CrossOrigin(origins = "http://localhost:8080")
    public Commodity getCommodityInfo_Response(@RequestParam(value = "id") int commodity_id) throws IOException {
        return Shopping_WebSite.get_Shopping_WebSite_instance().get_Commodity(commodity_id);
    }
}