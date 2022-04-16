package com.ntc.webgiay.service;


import com.ntc.webgiay.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    //Lấy tất cả các sản phẩm
    List<Product> getAllProduct();
}
