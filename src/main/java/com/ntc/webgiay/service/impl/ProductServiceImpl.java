
package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.entity.Product;
import com.ntc.webgiay.repository.ProductRepository;
import com.ntc.webgiay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> getListNewProducts(){
        List<Product> products = productRepository.getListNewProducts(3);
        return products;
    }
    //
    @Override
    public Product getDetailProductById(int id){
        Optional<Product> rs = productRepository.findById(id);
        if(rs.isEmpty()){
            throw new NotFoundException("Sản phẩm không tồn tại");
        }
        Product product = rs.get();
        return product;
    }


}
