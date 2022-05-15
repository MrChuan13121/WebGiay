package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query(nativeQuery= true, value ="Update dbshopgiay.category set brand_id = null where brand_id = ?1; Delete from dbshopgiay where id = ?1")
    void deleteBrand(int id);

}
