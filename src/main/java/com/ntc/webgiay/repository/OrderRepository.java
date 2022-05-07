package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    @Modifying
//    @Query(value = "insert into order_detail (product_id,order_id ) values ( :product_id, :order_id)",nativeQuery = true)
//    void createOrderDetail(@Param("product_id") int productId, @Param("order_id") int orderId);
}
