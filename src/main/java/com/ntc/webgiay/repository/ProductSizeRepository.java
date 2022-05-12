package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Product_size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<Product_size, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.product_size WHERE product_id = ?1 AND size_id = ?2")
    Product_size findByProductIdAndSizeId(int productId, int sizeId);
}
