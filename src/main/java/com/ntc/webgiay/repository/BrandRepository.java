package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.brand WHERE id = ?1")
    Brand getById(Integer id);
}
