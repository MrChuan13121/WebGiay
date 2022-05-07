package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Order createOrder(String nameReceiver,String phoneReceiver,String addressReceiver,String note,String price, int userId);
}
