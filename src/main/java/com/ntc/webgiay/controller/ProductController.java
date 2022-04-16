package com.ntc.webgiay.controller;

import com.ntc.webgiay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {



    @GetMapping("/single-product")
    public String pageSingleProduct(){
        return "single-product";
    }
}
