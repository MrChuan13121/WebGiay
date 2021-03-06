package com.ntc.webgiay.repository;


import com.ntc.webgiay.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(nativeQuery = true, value = "SELECT name_category FROM category WHERE brand_id = ?1")
    List<String> getListCategoryOfBrand(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.category ORDER BY id DESC ")
    List<Category> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM  dbshopgiay.category WHERE id = ?1")
    Category getById(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.category ORDER BY Id DESC")
    Page<Category> findAllOrderById(Pageable pageable);
}


