package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.CartItem;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.repository.ProductRepository;
import com.ntc.webgiay.service.ProductService;
import com.ntc.webgiay.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.Access;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class  ShoppingCartServiceImpl implements ShoppingCartService {

    Map<Integer, CartItem> maps = new HashMap<Integer, CartItem>();

    @Autowired
    ProductRepository productRepository;

    @Override
    public void add(CartItem item){
            CartItem cartItem = maps.get(item.getProductId());
            if( cartItem == null ){
                maps.put(item.getProductId(),item);
            }else{
                 cartItem.setQuantity(cartItem.getQuantity() + 1 );
            }
    }

    @Override
    public void remove(int id){
        maps.remove(id);
    }

    @Override
    public CartItem update(int productId, int quantity){
        CartItem cartItem = maps.get(productId);
        Product product = productRepository.getById(productId);
        int qty = product.getQuantity();
        if( qty >= quantity){
            cartItem.setQuantity(quantity);
        }else {
            cartItem.setQuantity(1);
        }



        return cartItem;
    }

    @Override
    public  void clear(){
        maps.clear();
    }

    @Override
    public Collection<CartItem> getAllItems(){
        return maps.values();
    }

    @Override
    public int getCount(){
        return maps.values().size();
    }

    @Override
    public  double getAmount(){
        return maps.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }
}
