package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.repository.BrandRepository;
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

    @Override
    public List<Brand> findAll(){
        List<Brand> listBrand = brandRepository.findAll();
        return listBrand;
    }

    @Override
    public Brand getById(Integer id){
        return brandRepository.getById(id);
    }

    @Override
    public Brand createBrand(String name, String description, String thumbnail){
        Brand brand = new Brand();
        brand.setNameBrand(name);
        brand.setDescription(description);
        brand.setThumbnail(thumbnail);
        brand.setStatus(false);
        brandRepository.save(brand);
        return brand;
    }

    @Override
    public Brand save(Brand brand){
        return brandRepository.save(brand);
    }

    @Override
    public  void deleteBrand(Integer id){
        brandRepository.delete(brandRepository.getById(id));
    }
}