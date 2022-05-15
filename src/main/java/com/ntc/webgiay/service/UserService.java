package com.ntc.webgiay.service;

import com.ntc.webgiay.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    List<Integer> findAllAdminId();

    User getById(int id);

}


