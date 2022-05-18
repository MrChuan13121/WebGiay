package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Category;
import com.ntc.webgiay.repository.BrandRepository;
import com.ntc.webgiay.repository.CategoryRepository;
import com.ntc.webgiay.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Category> findAllOrderById(Pageable pageable){
        Page<Category> pageList = categoryRepository.findAllOrderById(pageable);
        return pageList;
    }

}
