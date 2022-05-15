package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Category;
import com.ntc.webgiay.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Order createOrder(String nameReceiver,String phoneReceiver,String addressReceiver,String note,String price, int userId);

    List<Order> findAll();

    Order getById(int id);

    void updateStatus(int id);
}
