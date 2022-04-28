package com.ntc.webgiay.controller;



import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.service.BrandService;
import com.ntc.webgiay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {
    @Autowired
    BrandService brandService;

    @Autowired
    ProductService productService;


    //Trang danh sách sản phẩm
    @GetMapping("/products")
    public String showListProduct(Model model,@RequestParam( name = "name", required = false) String keyword,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size){


        //Lấy tên các thương hiệu
        List<Brand> brandsReputation = brandService.getBrandReputation();
        model.addAttribute("listBrandsReputation",brandsReputation);

//        //Tìm kiếm sản phẩm
//        List<Product> listProduct = productService.searchProducts(keyword);
//        model.addAttribute("listProduct", listProduct);
//        model.addAttribute("keyword",keyword);



        int currentPage = page.orElse(1);
        int sizePage = size.orElse(3);

        Pageable pageable = PageRequest.of(currentPage - 1,sizePage);
        Page<Product> resultPage = null;

        if (StringUtils.hasText(keyword) ){
            resultPage = productService.searchProducts(keyword,pageable);
            model.addAttribute("keyword",keyword);
        }else{
            resultPage = productService.findAll(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if( totalPages > 0){
            int start = Math.max(1, currentPage -2);
            int end = Math.min(currentPage + 2, totalPages);

            if(totalPages > 5){
                if( end == totalPages){
                    start = end - 5;
                }else if (start == 1){
                    end = start + 5;
                }
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumbers);
        }
        model.addAttribute("productPage",resultPage);
        return "category";

    }

//
//    @GetMapping("/products/paginated")
//    public String showListProductsByPage(ModelMap model,
//                                         @RequestParam(name = "keyword", required = false) String keyword,
//                                         @RequestParam("page") Optional<Integer> page,
//                                         @RequestParam("size") Optional<Integer> size){
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(5);
//        Pageable pageable = PageRequest.of(currentPage,pageSize);
//        Page<Product> resultPage = null;
//
//        if (StringUtils.hasText(keyword)){
//            resultPage = productService.findByNameContaining(keyword,pageable);
//            model.addAttribute("keyword",keyword);
//        }else{
//            resultPage = productService.findAll(pageable);
//        }
//
//        int totalPages = resultPage.getTotalPages();
//        if( totalPages > 0){
//            int start = Math.max(1, currentPage -2);
//            int end = Math.min(currentPage + 2, totalPages);
//
//            if(totalPages > 5){
//                if( end == totalPages){
//                    start = end - 5;
//                }else if (start == 1){
//                    end = start + 5;
//                }
//            }
//            List<Integer> pageNumbers = IntStream.rangeClosed(start,end)
//                .boxed()
//                .collect(Collectors.toList());
//            model.addAttribute("pageNumber", pageNumbers);
//        }
//        model.addAttribute("productPage",resultPage);
//
//        //Lấy tên các thương hiệu
//        List<Brand> brandsReputation = brandService.getBrandReputation();
//        model.addAttribute("listBrandsReputation",brandsReputation);
//
//        return "category";
//    }



}
