package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(nativeQuery = true, value = "SELECT SUM(price) FROM dbshopgiay.orders WHERE status = 1")
    float sumPrice ();

    @Query(nativeQuery = true, value = "UPDATE dbshopgiay.orders set status = true where id = ?1")
    void updateStatus(int id);

    @Query(nativeQuery = true, value = "SELECT COUNT(id)  FROM dbshopgiay.orders where status = 1 ")
    int countOrder();

    @Query(nativeQuery = true, value = "SELECT COUNT(id)  FROM dbshopgiay.orders where status = 0 ")
    int countDonHangCho();

    //lấy order có sắp xếp
    @Query(nativeQuery = true, value = "SELECT * FROM orders p  ORDER BY id DESC ")
    List<Order> getListOrder();

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.orders ORDER BY Id DESC")
    Page<Order> findAllOrderById(Pageable pageable);
//    @Modifying
//    @Query(value = "insert into order_detail (product_id,order_id ) values ( :product_id, :order_id)",nativeQuery = true)
//    void createOrderDetail(@Param("product_id") int productId, @Param("order_id") int orderId);
}
