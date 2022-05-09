package com.ntc.webgiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminsController {

    @GetMapping("/admin")
    public String adminPage(){
        return "admin/index";
    }

    @GetMapping("/admin/index")
    public String adminHome(){
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

    @GetMapping("/admin/categories")
    public String adminCategories(){
        return "admin/categories";
    }

    @GetMapping("/admin/brands")
    public String adminBrands(){
        return "admin/brands";
    }

    @GetMapping("/admin/users")
    public String adminUsers(){
        return "admin/users";
    }


}
