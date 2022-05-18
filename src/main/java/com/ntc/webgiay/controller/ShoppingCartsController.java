package com.ntc.webgiay.controller;

import com.ntc.webgiay.model.*;
import com.ntc.webgiay.repository.ProductSizeRepository;
import com.ntc.webgiay.repository.SizeRepository;
import com.ntc.webgiay.service.BrandService;
import com.ntc.webgiay.service.ProductService;
import com.ntc.webgiay.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartsController {
    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    BrandService brandService;
    @GetMapping("cart")
    public String list(Model model){
        //Lấy các thương hiệu
        List<Brand> brandsReputation = brandService.getBrandReputation();
        model.addAttribute("listBrandsReputation",brandsReputation);

        model.addAttribute("listItem",shoppingCartService.getAllItems());
        model.addAttribute("total",shoppingCartService.getAmount());
        return "cart";
    }
//
//    @GetMapping("add/{id}")
//    public String add(@PathVariable("id") Integer id){
//        Product product = productService.getDetailProductById(id);
//        if( product != null ){
//            CartItem item = new CartItem();
//            item.setName(product.getName());
//            item.setThumbnail(product.getThumbnail());
//            item.setPrice(product.getPrice());
//            item.setProductId(product.getId());
//            shoppingCartService.add(item);
//        }
//        return "redirect:/cart";
//    }

    @PostMapping("add/{id}")
    public String postProdcut(@PathVariable("id") Integer id, @RequestParam("size") int size , @RequestParam("qty") int qty ){
        Product product = productService.getDetailProductById(id);
        if( product != null ){
            CartItem item = new CartItem();
            item.setName(product.getName());
            item.setThumbnail(product.getThumbnail());
            item.setPrice(product.getPrice());
            item.setQuantity(qty);
            item.setSize(size);
            item.setProductId(product.getId());
            shoppingCartService.add(item,size);
        }
        return "redirect:/cart";
    }


    @GetMapping("delete/{id}")
    public String remove(@PathVariable("id") Integer id,@RequestParam("size") int size){
        shoppingCartService.remove(id,size);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id, @RequestParam("qty") int qty, @RequestParam("size") int size){
        shoppingCartService.update(id,qty,size);
        return "redirect:/cart";
    }

    @GetMapping("clear")
    public String clear(){
        shoppingCartService.clear();
        return "redirect:/cart";
    }
}
