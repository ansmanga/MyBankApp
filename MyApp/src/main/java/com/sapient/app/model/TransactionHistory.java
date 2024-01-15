package com.sapient.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name = "transaction_history")
public class TransactionHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String transactionType;
	private double amount;
	private String loan_type;
	private int years;
	
	private double interestAmount;

    public double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(double interestAmount) {
        this.interestAmount = interestAmount;
    }
	
	@ManyToOne
    @JoinColumn(name = "customer_aadhar")
	@JsonIgnore
    private Customer customer;
	
	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}
	
	
	public TransactionHistory()
	{
		
	}
	
	public TransactionHistory(String transactionType, double amount, Customer cus, String loan_type, int years) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.loan_type = loan_type;
        this.years = years;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }


}
