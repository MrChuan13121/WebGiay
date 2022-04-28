package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    //Lấy các thương hiệu danh tiếng
    List<Brand> getBrandReputation();

}
