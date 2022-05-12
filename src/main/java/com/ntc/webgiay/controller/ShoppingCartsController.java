package com.ntc.webgiay.controller;

import com.ntc.webgiay.model.CartItem;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.repository.SizeRepository;
import com.ntc.webgiay.service.ProductService;
import com.ntc.webgiay.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartsController {
    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    SizeRepository sizeRepository;

    @GetMapping("cart")
    public String list(Model model){

        model.addAttribute("listItem",shoppingCartService.getAllItems());
        model.addAttribute("total",shoppingCartService.getAmount());
        model.addAttribute("listSize",sizeRepository.findAll());
        return "cart";
    }

    @GetMapping("add/{id}")
    public String add(@PathVariable("id") Integer id){
        Product product = productService.getDetailProductById(id);
        if( product != null ){
            CartItem item = new CartItem();
            item.setName(product.getName());
            item.setThumbnail(product.getThumbnail());
            item.setPrice(product.getPrice());
            item.setProductId(product.getId());
            item.setSize(36);
            item.setQuantity(1);
            shoppingCartService.add(item);
        }
        return "redirect:/cart";
    }


    @GetMapping("delete/{id}")
    public String remove(@PathVariable("id") Integer id){
        shoppingCartService.remove(id);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id, @RequestParam("qty") int qty, @RequestParam("sizeId") int sizeId){
        shoppingCartService.update(id,qty,sizeId);
        return "redirect:/cart";
    }

    @GetMapping("clear")
    public String clear(){
        shoppingCartService.clear();
        return "redirect:/cart";
    }
}
