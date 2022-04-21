
package com.ntc.webgiay.repository;


import com.ntc.webgiay.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(nativeQuery = true, value = "SELECT p.id, p.name FROM product p WHERE p.status = 1 ORDER BY created_at DESC limit ?1")
    List<Product> getListNewProducts(int limit);

}
