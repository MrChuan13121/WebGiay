package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.Category;
import com.ntc.webgiay.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    //Lấy các thương hiệu danh tiếng
    List<Brand> getBrandReputation();

    List<Brand> findAll();

    Page<Brand> findAllOrderById(Pageable pageable);
}
