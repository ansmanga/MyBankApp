package com.sapient.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sapient.app.model.Customer;
import com.sapient.app.model.User;
import com.sapient.app.repository.BankingRepository;
import com.sapient.app.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BankingRepository bankingRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Customer> getusers() {
		return bankingRepository.findAll();
	}
	
	public Customer createUser(Customer cus)
	{	
		cus.setPassword(passwordEncoder.encode(cus.getPassword()));
		return bankingRepository.save(cus);
	}

}
