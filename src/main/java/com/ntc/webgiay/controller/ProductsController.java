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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductsController {
    @Autowired
    BrandService brandService;

    @Autowired
    ProductService productService;


    //Trang danh sách sản phẩm
    @GetMapping("/products")
    public String showListProduct(Model model, @RequestParam( name = "name", required = false) String keyword,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size){


        //Lấy tên các thương hiệu
        List<Brand> brandsReputation = brandService.getBrandReputation();
        model.addAttribute("listBrandsReputation",brandsReputation);


        //Tìm kiếm sản phẩm

        int currentPage = page.orElse(1);//Trang hiển thị
        int sizePage = size.orElse(8);//Kích thước sản phẩm trong 1 trang

        Pageable pageable = PageRequest.of(currentPage - 1,sizePage);

        Page<Product> listProduct = productService.searchProduct(keyword,pageable);//Lấy các
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("keyword",keyword);

        int totalPage = listProduct.getTotalPages();
        if( totalPage > 0 ){
            int start = Math.max(1,currentPage - 2);
            int end = Math.min(currentPage + 2,totalPage);
            if( totalPage > 5){
                if( end == totalPage ){
                    start = end - 5;
                }else if (start == 1){
                    end = start + 5;
                }
            }
            List<Integer> pageNumber = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumber);
        }

        return "category";

    }


}
