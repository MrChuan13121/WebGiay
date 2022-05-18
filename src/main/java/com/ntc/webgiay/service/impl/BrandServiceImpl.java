package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.Category;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.repository.BrandRepository;
import com.ntc.webgiay.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<Brand> findAll(){
        List<Brand> listBrand = brandRepository.findAll();
        return listBrand;
    }

    @Override
    public Page<Brand> findAllOrderById(Pageable pageable){
        Page<Brand> pageList = brandRepository.findAllOrderById(pageable);
        return pageList;
    }
}


