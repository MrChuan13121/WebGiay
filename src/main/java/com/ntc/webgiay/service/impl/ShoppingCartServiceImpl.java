package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.CartItem;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.model.Size;
import com.ntc.webgiay.repository.ProductRepository;
import com.ntc.webgiay.repository.SizeRepository;
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
public class   ShoppingCartServiceImpl implements ShoppingCartService {

    Map<Integer, CartItem> maps = new HashMap<Integer, CartItem>();

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SizeRepository sizeRepository;
    @Override
    public void add(CartItem item, int size){
            CartItem cartItem = maps.get(item.getProductId());
            if( cartItem == null ){
                maps.put(item.getProductId(),item);
            }else {
                if(cartItem.getSize() == size){
                    cartItem.setQuantity(cartItem.getQuantity() + 1 );
                }else{
                    maps.put(item.getSize(),item);
                }
            }

    }


    @Override
    public void remove(int id){
        maps.remove(id);
    }

    @Override
    public CartItem update(int productId, int quantity, int size){
        CartItem cartItem = maps.get(productId);
        CartItem cartItem1 = maps.getOrDefault(size,cartItem);
        Product product = productRepository.getById(productId);
        int qty = product.getQuantity();
        if( qty >= quantity){
            cartItem1.setQuantity(quantity);
        }else {
            cartItem1.setQuantity(1);
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
