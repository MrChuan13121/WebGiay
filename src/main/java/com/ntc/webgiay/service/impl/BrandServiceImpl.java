package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.entity.Brand;
import com.ntc.webgiay.entity.Category;
import com.ntc.webgiay.repository.BrandRepository;
import com.ntc.webgiay.repository.CategoryRepository;
import com.ntc.webgiay.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;


    @Override
    public List<Brand> getBrandReputation(){
        return brandRepository.findAll();
    }

}
