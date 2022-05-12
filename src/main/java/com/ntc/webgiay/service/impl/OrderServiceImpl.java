package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Category;
import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.User;
import com.ntc.webgiay.repository.OrderRepository;
import com.ntc.webgiay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order createOrder(String nameReceiver,String phoneReceiver,String addressReceiver,String note,String price, int userId){
        Order order = new Order();
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setNameReceiver(nameReceiver);
        order.setPhoneNumberReceiver(phoneReceiver);
        order.setAddressReceiver(addressReceiver);
        order.setPrice(Float.parseFloat(price));
        order.setNote(note);
        order.setStatus(true);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> findAll(){
        List<Order> listOrder = orderRepository.findAll();
        return listOrder;
    }
}


