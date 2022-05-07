package com.ntc.webgiay.controller;


import com.ntc.webgiay.model.CartItem;
import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.model.User;
import com.ntc.webgiay.repository.UserRepository;

import com.ntc.webgiay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Collection;



@Controller
public class OrdersController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDetailService orderDetailService;


    @PostMapping("/checkout")
    public String createOrder( @RequestParam("name_receiver") String nameReceiver,@RequestParam("phone_number_receiver") String phoneReceiver,@RequestParam("address_receiver") String addressReceiver,@RequestParam("note") String note, @RequestParam("price") String price, @RequestParam("userId") Integer id) {
        Order order = orderService.createOrder(nameReceiver,phoneReceiver,addressReceiver,note,price, id);
        Collection<CartItem> cartItemMap = shoppingCartService.getAllItems();
        for (var item : cartItemMap
             ) {
            Product product = productService.getDetailProductById(item.getProductId());
            orderDetailService.createOrderDetail(product.getId(),order.getId(),item.getQuantity());
        }
        shoppingCartService.clear();
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutCart(Model model){
            User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            model.addAttribute("user",userRepository.findByEmail(user.getEmail()));
            model.addAttribute("listItem",shoppingCartService.getAllItems());
            model.addAttribute("total",shoppingCartService.getAmount());
            return "checkout";
    }

}
