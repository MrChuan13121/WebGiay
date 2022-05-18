package com.ntc.webgiay.service.impl;


import com.ntc.webgiay.model.*;
import com.ntc.webgiay.repository.*;
import com.ntc.webgiay.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public OrderDetail createOrderDetail(int productId, int orderId, int qty, int size){
        OrderDetail orderDetail = new OrderDetail();
        Order order = orderRepository.getById(orderId);
        Product product = productRepository.getById(productId);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(qty);
        orderDetail.setSize(size);


        productRepository.save(product);
        orderDetailRepository.save(orderDetail);
        return orderDetail;
    }
}
