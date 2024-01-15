package com.sapient.app.dao;

import org.springframework.stereotype.Repository;

import com.sapient.app.model.Customer;
import com.sapient.app.model.TransactionHistory;

@Repository
public interface BankingDao {
	

	public Customer DepositMoney(double amount, Customer cus);
	public Customer WithdrawMoney(double amount, Customer cus);
	public TransactionHistory customerLoan(TransactionHistory th, Customer cus);
	public TransactionHistory customerFD(TransactionHistory th, Customer cus);
}
