package com.ntc.webgiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminsController {

    @GetMapping("/admin")
    public String adminPage(){
        return "admin/index";
    }

    @GetMapping("/admin/orders")
    public String adminOrder(){
        return "admin/orders";
    }

    @GetMapping("/admin/products")
    public String adminProducts(){
        return "admin/products";
    }

}
