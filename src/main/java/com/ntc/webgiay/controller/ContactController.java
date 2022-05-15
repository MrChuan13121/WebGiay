package com.ntc.webgiay.controller;

import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    BrandService brandService;
    @GetMapping("/contact")
    public String pageContact(Model model){

        //Lấy các thương hiệu
        List<Brand> brandsReputation = brandService.getBrandReputation();
        model.addAttribute("listBrandsReputation",brandsReputation);
        return "contact";
    }

}
