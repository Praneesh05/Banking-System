package com.example.model;

public class User {
    private String accountNo;
	private String name;
    private String email;
    private int deposit;
    private String gender;
    private String number;
    private String password;
    
    // No-argument constructor
    public User() {}

    // Constructor with all fields
	public User(String accountNo, String name, String email, int deposit, String gender, String number, String password) {
		this.accountNo = accountNo;
		this.name = name;
		this.email = email;
		this.deposit = deposit;
		this.gender = gender;
		this.number = number;
		this.password = password;
	}



	public String getName() {
		return name;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
