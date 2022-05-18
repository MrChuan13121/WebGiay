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

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.brand ORDER BY Id DESC")
    Page<Brand> findAllOrderById(Pageable pageable);

}
