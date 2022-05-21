package com.ntc.webgiay.service;

import com.ntc.webgiay.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Page<User> findAllUserOrderById(Pageable pageable);


    List<Integer> findAllAdminId();

    User getById(int id);

    void deleteUser(Integer id);
}


