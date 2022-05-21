package com.ntc.webgiay.repository;


import com.ntc.webgiay.model.Brand;
import com.ntc.webgiay.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT COUNT(id)  FROM dbshopgiay.users where status = 1 ")
    int countUser();
  
    @Query(nativeQuery = true, value ="SELECT id FROM dbshopgiay.user_roles where roles_id = 2")
    List<Integer> findUserIdAdmin();

    @Query(nativeQuery = true, value = "select * from dbshopgiay.users right join dbshopgiay.user_roles on dbshopgiay.users.id = dbshopgiay.user_roles.id group by dbshopgiay.user_roles.id having count(dbshopgiay.user_roles.id) < 2")
    Page<User> findAllUserOrderById(Pageable pageable);


}
