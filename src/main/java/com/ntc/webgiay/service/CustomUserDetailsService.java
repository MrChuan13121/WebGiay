package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Roles;
import com.ntc.webgiay.model.User;
import com.ntc.webgiay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Email của bạn chưa đăng ký!");
		}
		return new CustomUserDetails(user);
	}



}
