package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT SUM(price) FROM Order WHERE status = 1")
    float sumPrice ();

    @Query(nativeQuery = true, value = "UPDATE dbshopgiay.orders set status = true where id = ?1")
    void updateStatus(int id);

    @Query(nativeQuery = true, value = "SELECT COUNT(id)  FROM dbshopgiay.orders where status = 1 ")
    int countOrder();

    @Query(nativeQuery = true, value = "SELECT COUNT(id)  FROM dbshopgiay.orders where status = 0 ")
    int countDonHangCho();

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.orders WHERE id = ?1")
    Order getById(int id);


//    @Modifying
//    @Query(value = "insert into order_detail (product_id,order_id ) values ( :product_id, :order_id)",nativeQuery = true)
//    void createOrderDetail(@Param("product_id") int productId, @Param("order_id") int orderId);
}
