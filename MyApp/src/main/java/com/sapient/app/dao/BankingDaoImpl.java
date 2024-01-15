package com.sapient.app.dao;

import org.springframework.stereotype.Repository;

import com.sapient.app.model.Customer;
import com.sapient.app.model.TransactionHistory;

@Repository
public class BankingDaoImpl implements BankingDao{
	
	@Override
	public Customer DepositMoney(double amount, Customer cus) {
		
			cus.setBalance(cus.getBalance() + amount);
			
			return cus;
		
	}

	@Override
	public Customer WithdrawMoney(double amount, Customer cus) {
		
		
			if (cus.getBalance() - amount <= 1000) {
		        System.out.println("Withdrawal failed. Insufficient balance. Minimum balance should be 1000.");
		        return cus;
		    }

		
			cus.setBalance(cus.getBalance() - amount);

			
			return cus;
	}

	@Override
	public TransactionHistory customerLoan(TransactionHistory th, Customer cus) {
		
		double interestRate;

	    String loanType = th.getLoan_type().toLowerCase(); // Convert to lowercase
	    System.out.println(loanType);
	    double amount = th.getAmount();
	    int years = th.getYears();

	    switch (loanType) {
	        case "homeloan": // Update to lowercase
	            interestRate = 8.5;
	            break;
	        case "personalloan": // Update to lowercase
	            interestRate = 10.0;
	            break;
	        case "educationloan": // Update to lowercase
	            interestRate = 9.0;
	            break;
	        case "carloan": // Update to lowercase
	            interestRate = 7.5;
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid loan type: " + loanType);
	    }

	    double interestAmount = (amount * interestRate * years) / 100;
	    th.setInterestAmount(interestAmount);
	    return th;
		
	}

	@Override
	public TransactionHistory customerFD(TransactionHistory th, Customer cus) {
		
		double amount = th.getAmount(); 
	    int years = th.getYears();
	    
	    double interestRate = 6.0;
	    
	    double interestAmount = (amount * interestRate* years) / 100;
	    th.setInterestAmount(interestAmount);
	    
	    return th;
	    
		
	}
	
	
	
	
	
	

}
