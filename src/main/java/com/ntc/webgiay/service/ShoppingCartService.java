package com.ntc.webgiay.service;

import com.ntc.webgiay.model.CartItem;

import java.util.Collection;

public interface ShoppingCartService {
    void add(CartItem item, int size, int quantity);

    void remove(int id, int size);

    CartItem update(int productId, int quantity, int size);

    void clear();

    Collection<CartItem> getAllItems();

    int getCount();

    double getAmount();
}
