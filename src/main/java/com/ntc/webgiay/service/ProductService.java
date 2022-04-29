
package com.ntc.webgiay.service;


import com.ntc.webgiay.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {


    //Lấy sản 3 phẩm mới nhất
    List<Product> getListNewProducts();

    //Lấy danh sách các sản phẩm nổi bật
    List<Product> findAll();

    //Lấy thông tin sản phẩm theo id
    Product getDetailProductById(int id);

    //Lấy danh sách các sản phẩm và tìm kiếm
    Page<Product> searchProduct(String keyword, Pageable pageable);
//
//    //Tìm kiếm sản phẩm và phân trang
//    Page<Product> searchProducts(String keyword, Pageable pageable);
//
//    Page<Product> findAll(Pageable pageable);
}
