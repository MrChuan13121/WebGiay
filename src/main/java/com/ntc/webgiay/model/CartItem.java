package com.ntc.webgiay.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    Integer productId;
    String name;
    String thumbnail;
    int quantity;
    float price;
}
