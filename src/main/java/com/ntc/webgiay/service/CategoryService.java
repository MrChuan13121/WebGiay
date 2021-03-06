package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Category;
import com.ntc.webgiay.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    //Lấy thể loại theo mã thương hiệu
    List<String> getListCategoryOfBrand(int id);

    //lấy tất cả loại
    List<Category> findAll();

    Page<Category> findAllOrderById(Pageable pageable);

    Category createCategory(String category, int brandId);

    Category getById(int id);

    Category update(int id, String category, int brandId);
}
