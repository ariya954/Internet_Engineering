package com.example.Baloot_Shopping_Center_Phase4.APIs;

import com.example.Baloot_Shopping_Center_Phase4.Provider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase4.Shopping_WebSite;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class getProviderInfo {
    @RequestMapping(value = "/getProviderInfo")
    public Provider getProviderInfo_Response(@RequestParam(value = "id") int provider_id) throws IOException, SQLException {
        return Shopping_WebSite.get_Shopping_WebSite_instance().get_Provider(provider_id);
    }
}