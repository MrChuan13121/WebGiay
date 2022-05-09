package com.ntc.webgiay.service.impl;


import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.OrderDetail;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.repository.OrderDetailRepository;
import com.ntc.webgiay.repository.OrderRepository;
import com.ntc.webgiay.repository.ProductRepository;
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

    @Override
    public OrderDetail createOrderDetail(int productId, int orderId, int qty){
        OrderDetail orderDetail = new OrderDetail();
        Order order = orderRepository.getById(orderId);
        Product product = productRepository.getById(productId);
        int quantity = product.getQuantity();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(qty);
        product.setQuantity(quantity - qty);
        productRepository.save(product);
        orderDetailRepository.save(orderDetail);
        return orderDetail;
    }
}
