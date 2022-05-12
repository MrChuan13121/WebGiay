package com.ntc.webgiay.controller;

import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.OrderDetail;
import com.ntc.webgiay.repository.OrderDetailRepository;
import com.ntc.webgiay.repository.OrderRepository;
import com.ntc.webgiay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminsController {

    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/admin")
    public String adminPage(){
        return "admin/index";
    }

    @GetMapping("/admin/index")
    public String adminHome(){
        return "admin/index";
    }

    @GetMapping("/admin/orders")
    public String adminOrder(Model model){
        model.addAttribute("listOrder",orderService.findAll());
        return "admin/orders";
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model){
        model.addAttribute("listProduct",productService.findAll());
        return "admin/products";
    }

    @GetMapping("/admin/categories")
    public String adminCategories(Model model){
        model.addAttribute("listCategory", categoryService.findAll());
        return "admin/categories";
    }

    @GetMapping("/admin/brands")
    public String adminBrands(Model model){
        model.addAttribute("listBrand", brandService.findAll());
        return "admin/brands";
    }

    @GetMapping("/admin/users")
    public String adminUsers(){
        return "admin/users";
    }

    @GetMapping("/admin/orderDetail")
    public String adminOrderDetail(){
        return "admin/orderDetail";
    }

    @GetMapping("/admin/orderDetail/{id}")
    public String getOrderDetail(Model model,@PathVariable("id") int id){
        OrderDetail orderDetail = orderDetailRepository.getById(id);
        Order order = orderRepository.getById(orderDetail.getOrder().getId());
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(order.getId());

        model.addAttribute("order",order);
        model.addAttribute("listOrderDetail",orderDetailList);
        return "admin/orderDetail";
    }


}
