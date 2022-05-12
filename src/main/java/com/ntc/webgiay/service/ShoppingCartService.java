package com.ntc.webgiay.service;

import com.ntc.webgiay.model.CartItem;

import java.util.Collection;

public interface ShoppingCartService {
    void add(CartItem item);

    void remove(int id);

    CartItem update(int productId, int quantity, int sizeId);

    void clear();

    Collection<CartItem> getAllItems();

    int getCount();

    double getAmount();
}
