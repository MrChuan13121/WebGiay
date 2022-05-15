
package com.ntc.webgiay.repository;


import com.ntc.webgiay.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query(nativeQuery = true, value = "SELECT p.id, p.name FROM product p WHERE p.status = 1 ORDER BY created_at DESC limit ?1")
    List<Product> getListNewProducts(int limit);

    //Tìm kiếm sản phẩm
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    Page<Product> searchProduct(String keyword,Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    //lấy  sản phẩm
    @Query(nativeQuery = true, value = "SELECT COUNT( p.id)  FROM product p ")
    int countProduct();
//
//    //Tìm kiếm cộng phân trang
//    @Query(nativeQuery = true, value ="SELECT p FROM Product p WHERE p.name LIKE %%?1%%")
//    Page<Product> searchProducts(String keyword,Pageable pageable);




}
