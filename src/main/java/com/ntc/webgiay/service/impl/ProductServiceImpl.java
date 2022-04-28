
package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.repository.ProductRepository;
import com.ntc.webgiay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll(){
        List<Product> listProduct = productRepository.findAll();
        return listProduct;
    }

    @Override
    public List<Product> getListNewProducts(){
        List<Product> products = productRepository.getListNewProducts(3);
        return products;
    }

    @Override
    public Product getDetailProductById(int id){
        Optional<Product> rs = productRepository.findById(id);
        if(rs.isEmpty()){
            throw new NotFoundException("Sản phẩm không tồn tại");
        }
        Product product = rs.get();
        return product;
    }

//    //Tìm kiếm sản phẩm
//    @Override
//    public List<Product> searchProducts(String keyword){
//        if(keyword != null){
//            return productRepository.searchProducts(keyword);
//        }else{
//            return productRepository.findAll();
//        }
//    }
    @Override
    public Page<Product> findAll(Pageable pageable){

        return productRepository.findAll(pageable);
    }

    //Tìm kiếm sản phẩm và phân trang
    @Override
    public Page<Product> searchProducts(String keyword, Pageable pageable){
        return productRepository.searchProducts(keyword,pageable);
    }
}
