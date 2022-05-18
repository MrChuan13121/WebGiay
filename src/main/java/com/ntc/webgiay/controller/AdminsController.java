package com.ntc.webgiay.controller;

import com.ntc.webgiay.model.*;
import com.ntc.webgiay.repository.OrderDetailRepository;
import com.ntc.webgiay.repository.OrderRepository;
import com.ntc.webgiay.repository.ProductRepository;
import com.ntc.webgiay.repository.UserRepository;
import com.ntc.webgiay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/admin")
    public String adminPage(){
        return "admin/index";
    }

    @GetMapping("/admin/index")
    public String adminHome(Model model){

        model.addAttribute("totalProduct",productRepository.countProduct());
        model.addAttribute("sumPrice", orderRepository.sumPrice());
        model.addAttribute("countUser",userRepository.countUser());
        model.addAttribute("countOrder", orderRepository.countOrder());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/index";
    }

    @GetMapping("/admin/orders")
    public String adminOrder(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        /*model.addAttribute("listOrder",orderRepository.getListOrder());*/
        int currentPage = page.orElse(1);
        int sizePage = size.orElse(10);

        Pageable pageable = PageRequest.of(currentPage - 1, sizePage);
        Page<Order> listOrder = orderService.findAllOrderById(pageable);
        model.addAttribute("listOrder", listOrder);
        int totalPage = listOrder.getTotalPages();
        if (totalPage > 0 ){
            int start = Math.max(1,currentPage-2);
            int end = Math.min(currentPage + 2, totalPage);
            if( totalPage > 5 ){
                if( end == totalPage){
                    start = end - 5;
                }else if(start == 1){
                    end = start + 5;
                }
            }
            List<Integer> pagenummber = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pagenummber);
        }
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/orders";
    }

    @PostMapping("/accept")
    public String acceptOrder(@RequestParam("orderId") Integer id){
        orderService.updateStatus(id);
        return "redirect:admin/orders";
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        /*model.addAttribute("listProduct",productService.findAll());*/

        int currentPage = page.orElse(1);
        int sizePage = size.orElse(10);

        Pageable pageable = PageRequest.of(currentPage - 1, sizePage);
        Page<Product> listProduct = productService.findAllOrderById(pageable);
        model.addAttribute("listProduct", listProduct);
        int totalPage = listProduct.getTotalPages();
        if (totalPage > 0 ){
            int start = Math.max(1,currentPage-2);
            int end = Math.min(currentPage + 2, totalPage);
            if( totalPage > 5 ){
                if( end == totalPage){
                    start = end - 5;
                }else if(start == 1){
                    end = start + 5;
                }
            }
            List<Integer> pagenummber = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pagenummber);
        }
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/products";
    }

    @GetMapping("/admin/categories")
    public String adminCategories(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int sizePage = size.orElse(10);

        Pageable pageable = PageRequest.of(currentPage - 1, sizePage);
        Page<Category> listCategory = categoryService.findAllOrderById(pageable);
        model.addAttribute("listCategory", listCategory);
        int totalPage = listCategory.getTotalPages();
        if (totalPage > 0 ){
            int start = Math.max(1,currentPage-2);
            int end = Math.min(currentPage + 2, totalPage);
            if( totalPage > 5 ){
                if( end == totalPage){
                    start = end - 5;
                }else if(start == 1){
                    end = start + 5;
                }
            }
            List<Integer> pagenummber = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pagenummber);
        }
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/categories";
    }

    @GetMapping("/admin/brands")
    public String adminBrands(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int sizePage = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage - 1, sizePage);
        Page<Brand> listBrand = brandService.findAllOrderById(pageable);
        model.addAttribute("listBrand", listBrand);

        int totalPage = listBrand.getTotalPages();
        if (totalPage > 0 ){
            int start = Math.max(1,currentPage-2);
            int end = Math.min(currentPage + 2, totalPage);
            if( totalPage > 5 ){
                if( end == totalPage){
                    start = end - 5;
                }else if(start == 1){
                    end = start + 5;
                }
            }
            List<Integer> pagenummber = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pagenummber);
        }
/*
        model.addAttribute("listBrand", brandService.findAll());
*/
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/brands";
    }

    @GetMapping("/admin/users")
    public String adminUsers(Model model){
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/users";
    }

    @GetMapping("/admin/orderDetail")
    public String adminOrderDetail(Model model){
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/orderDetail";
    }

    @GetMapping("/admin/orderDetail/{id}")
    public String getOrderDetail(Model model,@PathVariable("id") int id){
        Order order = orderService.getById(id);
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(order.getId());

        model.addAttribute("order",order);
        model.addAttribute("listOrderDetail",orderDetailList);
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/orderDetail";
    }

    @GetMapping("/admin/createBrand")
    public String adminCreateBrand(Model model){
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/createBrand";
    }

    @GetMapping("/admin/createCategory")
    public String adminCreateCategory(Model model){
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/createCategory";
    }

    @GetMapping("/admin/createProduct")
    public String adminCreateProduct(Model model){
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/createProduct";
    }
}
