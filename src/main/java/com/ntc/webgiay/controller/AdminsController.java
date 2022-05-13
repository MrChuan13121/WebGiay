package com.ntc.webgiay.controller;

import com.ntc.webgiay.model.*;
import com.ntc.webgiay.repository.OrderDetailRepository;
import com.ntc.webgiay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
    UserService userService;

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
    public String adminUsers(Model model){
        List<Integer> listAdminId = userService.findAllAdminId();
        List<User> listUser = userService.findAll();
        for( var item : listAdminId){
            User user = userService.getById(item);
            listUser.remove(user);
        }
        model.addAttribute("listUser",listUser);
        return "admin/users";
    }

    @GetMapping("/admin/orderDetail")
    public String adminOrderDetail(){
        return "admin/orderDetail";
    }

    @GetMapping("/admin/orderDetail/{id}")
    public String getOrderDetail(Model model,@PathVariable("id") int id){
        Order order = orderService.getById(id);
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(order.getId());

        model.addAttribute("order",order);
        model.addAttribute("listOrderDetail",orderDetailList);
        return "admin/orderDetail";
    }

    @GetMapping("/admin/createBrand")
    public String adminCreateBrand(){
        return "admin/createBrand";
    }

    @GetMapping("/admin/createCategory")
    public String adminCreateCategory(){
        return "admin/createCategory";
    }

    @GetMapping("/admin/createProduct")
    public String adminCreateProduct(){
        return "admin/createProduct";
    }



}
