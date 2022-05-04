package com.ntc.webgiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoriesController {
    @GetMapping("/category")
    public  String pageCategory(){

        return "category";
    }

}
