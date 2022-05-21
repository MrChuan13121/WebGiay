package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Product_size;
import org.springframework.stereotype.Service;

@Service
public interface Product_SizeService {

    Product_size getByProductIdAndSizeId(int productId, int sizeId);
}
