package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query(nativeQuery = true, value = "select * from dbshopgiay.sizes where number_size = ?1")
    Size getByName(int size);

    @Query("SELECT s FROM Size s WHERE s.Id = ?1")
    Size getById(int id);


}
