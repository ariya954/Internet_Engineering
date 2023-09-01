package com.example.baloot_shoppingcenter_phase2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User_Discount {
    private String discount_code;
    private int discount;
    private Boolean is_expired = false;

    @JsonCreator
    User_Discount(@JsonProperty("discountCode")String given_discount_code, @JsonProperty("discount")int given_discount) {
        discount_code = given_discount_code;
        discount = given_discount;
    }

    public String get_discount_code() { return discount_code; }
    public int get_discount() { return discount; }
    public Boolean is_expired() { return is_expired; }
    public void expire_discount() { is_expired = true; }
}