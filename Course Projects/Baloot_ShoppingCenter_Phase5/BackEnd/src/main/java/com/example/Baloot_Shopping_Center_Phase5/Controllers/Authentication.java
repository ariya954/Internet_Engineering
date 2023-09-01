package com.example.Baloot_Shopping_Center_Phase5.Controllers;

import com.example.Baloot_Shopping_Center_Phase5.Shopping_WebSite;
import com.example.Baloot_Shopping_Center_Phase5.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baloot_Shopping_Center_Phase5.FronEnd_Pages_Handlers.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class Authentication {
    @RequestMapping(value = "/authentication")
    public String Login_Page_Response(@RequestParam(value = "username", required = false, defaultValue = "") String input_username, @RequestParam(value = "password", required = false, defaultValue = "") String input_password, @RequestParam(value = "create_account", required = false, defaultValue = "0") boolean create_account) throws IOException, SQLException {
        if(create_account)
            Shopping_WebSite.get_Shopping_WebSite_instance().add_user(input_username, input_password, "", "", "", 0);
        if(!input_username.isEmpty() && !input_password.isEmpty()){
            User logged_in_user = Shopping_WebSite.get_Shopping_WebSite_instance().get_User(input_username);
            if(logged_in_user == null) {
                Login_Page_Handler page_handler = new Login_Page_Handler();
                return page_handler.Response();
            }
            User_Page_Handler page_handler = new User_Page_Handler();
            return page_handler.Response(logged_in_user.getUsername(), 0, false, 0, false, false);
        }
        Login_Page_Handler page_handler = new Login_Page_Handler();
        return page_handler.Response();
    }
}