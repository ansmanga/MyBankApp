package com.sapient.app.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.sapient.app.model.Customer;
import com.sapient.app.model.TransactionHistory;

@Service
public interface BankingService {
	
	public List<Customer> getCustomers();
	public Customer depositMoney(double amount, Customer cus, TransactionHistory th);
	public Customer withdrawMoney(double amount, Customer cus, TransactionHistory th);
	public Customer addCustomer(Customer cus);
	public Customer customerByAadhar(String aadhar);
	public void deleteCustomer(String aadhar);
	public TransactionHistory customerFD(TransactionHistory th, Customer cus);
	public TransactionHistory customerLoan(TransactionHistory th, Customer cus);
	public void depositTransaction(String aadhar, TransactionHistory th);
	public void withdrawTransaction(String aadhar, TransactionHistory th);
	public List<TransactionHistory> getTransactionHistoryByAadhar(Customer cus);
	public Customer authenticateuser(String email, String password);
	public Customer findUser(String userName);

}
