package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Category;
import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.User;
import com.ntc.webgiay.repository.OrderRepository;
import com.ntc.webgiay.repository.UserRepository;
import com.ntc.webgiay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public Order createOrder(String nameReceiver,String phoneReceiver,String addressReceiver,String note,String price, int userId){
        Order order = new Order();
        User user = userRepository.getById(userId);
        order.setUser(user);
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setNameReceiver(nameReceiver);
        order.setPhoneNumberReceiver(phoneReceiver);
        order.setAddressReceiver(addressReceiver);
        order.setPrice(Float.parseFloat(price));
        order.setNote(note);
        order.setStatus(false);
        orderRepository.save(order);
        user.setStatus(true);
        return order;
    }

    @Override
    public List<Order> findAll(){
        List<Order> listOrder = orderRepository.findAll();
        return listOrder;
    }


    @Override
    public Page<Order> findAllOrderById(Pageable pageable){
        Page<Order> pageList = orderRepository.findAllOrderById(pageable);
        return pageList;
    }
    @Override
    public Order getById(int id){
        return orderRepository.getById(id);
    }

    @Override
    public void updateStatus(int id){
        orderRepository.updateStatus(id);
    }

}


