package com.sapient.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sapient.app.model.Customer;
import com.sapient.app.model.User;
import com.sapient.app.repository.BankingRepository;
import com.sapient.app.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private BankingRepository bankingRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Customer customer = bankingRepository.findByEmail(username);
		return customer;
		
	}


}
