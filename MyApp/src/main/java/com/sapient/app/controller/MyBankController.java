package com.sapient.app.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.app.exceptions.CustomerAlreadyExistsException;
import com.sapient.app.model.Customer;
import com.sapient.app.model.TransactionHistory;
import com.sapient.app.service.BankingService;
import com.sapient.app.service.CustomUserDetailService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class MyBankController {
	
	
	@Autowired
	BankingService mservice;
	
	@Autowired
	AuthController authController;
	
	
	Customer authenticatedCustomer;
	
	@GetMapping("/balance")
	public double getCurrentBalance()
	{
		return authenticatedCustomer.getBalance();
	}
	
	@GetMapping("/all")
	public List<Customer> getCustomers()
	{
		
		return mservice.getCustomers();
		
	}
	
	@GetMapping("/byAadhar/{aadhar}")
	public Customer getCustomerByAadhar(@PathVariable("aadhar") String aadhar) {
        return mservice.customerByAadhar(aadhar);
    }
	
	@GetMapping("/current")
	public Customer getCustomer()
	{
		String userName = authController.getUsername();
		authenticatedCustomer = mservice.findUser(userName);
		return authenticatedCustomer;
	}
	
	@GetMapping("/transactions")
	public List<TransactionHistory> getTransactionHistory() {
        return mservice.getTransactionHistoryByAadhar(authenticatedCustomer);
    }
	
	@PostMapping("/add")
	public Customer insertCustomer(@Valid @RequestBody Customer cus) {
		
		String aadhar = cus.getAadhar();
		Customer localCustomer = getCustomerByAadhar(aadhar);
		if (localCustomer != null) {
            // Customer with the given Aadhar already exists, throw exception
            throw new CustomerAlreadyExistsException("Customer with Aadhar " + aadhar + " already exists.");
        }
		
		return mservice.addCustomer(cus);
		
	}
	

	
	@PutMapping("/deposit/{amount}")
	public Customer updateCustomerDepositBalance(@PathVariable ("amount") double amount, @Valid @RequestBody TransactionHistory th) {
		return mservice.depositMoney(amount, authenticatedCustomer, th);
		
	}
	
	@PutMapping("/withdraw/{amount}")
	public Customer updateCustomerWithdrawBalance(@PathVariable ("amount") double amount, @Valid @RequestBody TransactionHistory th) {
		
		return mservice.withdrawMoney(amount, authenticatedCustomer, th);
		
	}
	
	@PostMapping("/applyLoan")
	public TransactionHistory  applyForLoan(@Valid @RequestBody TransactionHistory th)
	{
		return mservice.customerLoan(th, authenticatedCustomer);
	}
	
	@PostMapping("/applyFD")
	public TransactionHistory applyForFD(@Valid @RequestBody TransactionHistory th)
	{
		return mservice.customerFD(th, authenticatedCustomer);
	}
	
	
	
	@PostMapping("/authenticate")
    public ResponseEntity<?> authenticateuser(@RequestBody Customer cus) {
				
        String email = cus.getEmail();
        String password = cus.getPassword();

        authenticatedCustomer = mservice.authenticateuser(email, password);
        if (authenticatedCustomer != null) {
            // Authentication successful, redirect or return a token
            return ResponseEntity.ok().build();
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
	
	@DeleteMapping("/deleteByAadhar/{aadhar}")
	public ResponseEntity<HttpStatus> deleteCustomerByAadhar(@PathVariable("aadhar") String aadhar) {
		try {
			mservice.deleteCustomer(aadhar);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	
	

}
