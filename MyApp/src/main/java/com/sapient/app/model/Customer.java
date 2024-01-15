package com.sapient.app.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer implements UserDetails{
    
	@NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain letters and spaces")
    private String name;
	@NotNull
    @Email(message = "Email should be in proper format")
	@Column(unique = true)
    private String email;
	@NotNull
    @Size(min = 12, max = 12, message = "Aadhar number must be of 12 digits")
    @Pattern(regexp = "\\d+", message = "Aadhar number must contain only digits")
    @Id
    private String aadhar;
	@NotNull
    private double balance;
    
    @NotNull
    @Size(min = 1, message = "Address cannot be null or empty")
    private String address;
    @NotNull
    private String password;
    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must be of 10 digits")
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    private String phoneNumber;
    
    public Customer() {
        
    }
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionHistory> transactions;
    
    
    public Customer(String name, String email, String aadhar, double balance, String address, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.aadhar = aadhar;
        this.balance = balance;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

   
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getUsername() {
		return this.email;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	

	
}
