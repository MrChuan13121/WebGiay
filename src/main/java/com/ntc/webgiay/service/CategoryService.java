package com.ntc.webgiay.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    //Lấy thể theo mã thương hiệu
    List<String> getListCategoryOfBrand(int id);
}
