package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.brand WHERE id = ?1")
    Brand getById(Integer id);

    @Query(nativeQuery= true, value ="Update dbshopgiay.category set brand_id = null where brand_id = ?1; Delete from dbshopgiay where id = ?1")
    void deleteBrand(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.brand ORDER BY Id DESC")
    Page<Brand> findAllOrderById(Pageable pageable);

}
