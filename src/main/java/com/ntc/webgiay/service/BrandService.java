package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BrandService {

    //Lấy các thương hiệu danh tiếng
    List<Brand> getBrandReputation();

    List<Brand> findAll();


    Brand getById(Integer id);

    Brand createBrand(String name, String description, String thumbnail);

    Brand save(Brand brand);

    void deleteBrand(Integer id);
}
