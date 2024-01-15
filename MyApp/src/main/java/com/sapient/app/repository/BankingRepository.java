package com.sapient.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.app.model.Customer;

public interface BankingRepository extends JpaRepository<Customer,String>{

	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String username);
		
	
}
