package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.OrderDetail;
import com.ntc.webgiay.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {
    OrderDetail createOrderDetail(int productId, int orderId , int qty);
}
