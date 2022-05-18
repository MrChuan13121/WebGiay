package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.Category;
import com.ntc.webgiay.repository.BrandRepository;
import com.ntc.webgiay.repository.CategoryRepository;
import com.ntc.webgiay.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<String> getListCategoryOfBrand(int id){
        return categoryRepository.getListCategoryOfBrand(id);
    }


    @Override
    public List<Category> findAll(){
        List<Category> listCategory = categoryRepository.findAll();
        return listCategory;
    }

    @Override
    public Category createCategory(String category, int brandId){
        Category category1 = new Category();
        category1.setNameCategory(category);
        category1.setStatus(false);
        category1.setBrand(brandRepository.getById(brandId));
        categoryRepository.save(category1);
        return category1;
    }

    @Override
    public Category getById(int id){
        return categoryRepository.getById(id);
    }

    @Override
    public Category update(int id, String category, int brandId){
        Category category1 = categoryRepository.getById(id);
        category1.setNameCategory(category);
        Brand brand = brandRepository.getById(brandId);
        category1.setBrand(brand);
        categoryRepository.save(category1);
        return  category1;
    }

}
