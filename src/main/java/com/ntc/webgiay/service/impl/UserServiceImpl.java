package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.User;
import com.ntc.webgiay.repository.UserRepository;
import com.ntc.webgiay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> findAllUserOrderById(Pageable pageable){
        Page<User> listUser = userRepository.findAllUserOrderById(pageable);
        return listUser;
    }

    @Override
    public List<Integer> findAllAdminId(){
        List<Integer> listAdminId = userRepository.findUserIdAdmin();
        return listAdminId;
    }

    @Override
    public User getById(int id){
        User user = userRepository.getById(id);
        return user;
    }

    @Override
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
