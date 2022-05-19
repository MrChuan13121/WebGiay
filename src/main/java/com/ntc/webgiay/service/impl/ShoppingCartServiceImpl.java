package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.CartItem;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.model.Product_size;
import com.ntc.webgiay.model.Size;
import com.ntc.webgiay.repository.ProductRepository;
import com.ntc.webgiay.repository.ProductSizeRepository;
import com.ntc.webgiay.repository.SizeRepository;
import com.ntc.webgiay.service.ProductService;
import com.ntc.webgiay.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.Access;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionScope
public class   ShoppingCartServiceImpl implements ShoppingCartService {

    Map<Integer, CartItem> maps = new HashMap<Integer, CartItem>();

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;
    @Override
    public void add(CartItem item, int size){
            CartItem cartItem = maps.get(item.getProductId());
            if( cartItem == null ){
                maps.put(item.getSize(),item);
            }else {
                if(cartItem.getSize() == size){
                    cartItem.setQuantity(cartItem.getQuantity() + 1 );
                }else{
                    maps.put(item.getSize(),item);
                }
            }

    }


    @Override
    public void remove(int id , int size){
            maps.remove(size);
    }

    @Override
    public CartItem update(int productId, int quantity, int size){
        CartItem cartItem = maps.get(productId);
        CartItem cartItem1 = maps.getOrDefault(size,cartItem);
        Size size1 = sizeRepository.getByName(size);
        Product_size product_size = productSizeRepository.findByProductIdAndSizeId(productId,size1.getId());

        int qty = product_size.getQuantity();
        if( qty >= quantity){
            cartItem1.setQuantity(quantity);
        }else {
            cartItem1.setQuantity(1);
        }

        return cartItem1;
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
