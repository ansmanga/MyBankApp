package com.sapient.app.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.app.dao.BankingDao;
import com.sapient.app.model.Customer;
import com.sapient.app.model.TransactionHistory;
import com.sapient.app.repository.BankingRepository;
import com.sapient.app.repository.TransactionHistoryRepository;

@Service
public class BankingServiceImpl implements BankingService{
	
	@Autowired
	BankingDao mobject;
	@Autowired
	BankingRepository robject;
	@Autowired
	TransactionHistoryRepository tobject;
	
	@Override
	public List<Customer> getCustomers()
	{
		return robject.findAll();
	}
	
	@Override
	public Customer depositMoney(double amount, Customer cus, TransactionHistory th)
	{
		
		Customer localCustomer =  mobject.DepositMoney(amount, cus);
		robject.save(localCustomer);
		th.setCustomer(localCustomer);
		th.setTransactionType("Deposit");
		th.setAmount(amount);
		tobject.save(th);
		return localCustomer;
	}

	@Override
	public Customer withdrawMoney(double amount, Customer cus, TransactionHistory th) {
		
		Customer localCustomer =  mobject.WithdrawMoney(amount, cus);
		robject.save(localCustomer);
		th.setCustomer(localCustomer);
		th.setTransactionType("Withdraw");
		th.setAmount(amount);
		tobject.save(th);
		return localCustomer;
	}

	@Override
	public Customer addCustomer(Customer cus) {
		return robject.save(cus);
	}

	
	@Override
	public Customer customerByAadhar(String aadhar) {
		java.util.Optional<Customer> optionalCustomer = robject.findById(aadhar);
	    return optionalCustomer.orElse(null);
	}

	@Override
	public void deleteCustomer(String aadhar) {
		@SuppressWarnings("deprecation")
		Customer localCustomer = robject.getById(aadhar);
		robject.delete(localCustomer);
	}

	@Override
	public TransactionHistory customerLoan(TransactionHistory th, Customer cus) {
		
		TransactionHistory th1 = mobject.customerLoan(th, cus);
		th1.setCustomer(cus);
		th1.setTransactionType("Loan");
		return tobject.save(th1);
	}

	@Override
	public TransactionHistory customerFD(TransactionHistory th, Customer cus) {
		
		TransactionHistory th1 = mobject.customerFD(th, cus);
		th1.setCustomer(cus);
		th1.setTransactionType("FD");
		return tobject.save(th1);
	}

	@Override
	public void depositTransaction(String aadhar, TransactionHistory th) {
		Customer localCustomer = robject.findById(aadhar).orElseThrow(() -> new RuntimeException("Customer not found"));
		th.setCustomer(localCustomer);
		tobject.save(th);
		
	}
	
	@Override
	public void withdrawTransaction(String aadhar, TransactionHistory th) {
		Customer localCustomer = robject.findById(aadhar).orElseThrow(() -> new RuntimeException("Customer not found"));
		th.setCustomer(localCustomer);
		tobject.save(th);
		
	}

	@Override
	public List<TransactionHistory> getTransactionHistoryByAadhar(Customer cus) {
		
		return tobject.findByCustomerAadhar(cus.getAadhar());
	}

	@Override
	public Customer authenticateuser(String email, String password) {
		
		return robject.findByEmailAndPassword(email, password);
		
	}

	@Override
	public Customer findUser(String userName) {
		// TODO Auto-generated method stub
		return robject.findByEmail(userName);
	}
	
	
	

}
