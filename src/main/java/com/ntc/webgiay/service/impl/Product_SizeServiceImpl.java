package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Product_size;
import com.ntc.webgiay.repository.ProductSizeRepository;
import com.ntc.webgiay.service.Product_SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Product_SizeServiceImpl implements Product_SizeService {
    @Autowired
    ProductSizeRepository productSizeRepository;

    @Override
    public Product_size getByProductIdAndSizeId(int productId, int sizeId){
        Product_size product_size = productSizeRepository.findByProductIdAndSizeId(productId,sizeId);
        return product_size;
    }
}
