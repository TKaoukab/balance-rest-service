package com.blue.harvest.beans;

import java.util.Arrays;
import java.util.List;

public class Customer {

	private int customerId;
	
	private String name;
	
	private String surname;
	
	private List<Account> transactionList;
	
	private double balance;
	
	public Customer() {}
	
	public Customer(int customerId, String name, String surname) {
		this.customerId = customerId;
		this.name = name;
		this.surname = surname;
	}

	public Customer(int customerId, String name, String surname, Account customerAccount) {
		this.customerId = customerId;
		this.name = name;
		this.surname = surname;
		if(this.transactionList == null) 
			this.transactionList = Arrays.asList(customerAccount);
		else
			this.transactionList.add(customerAccount);
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the customerAccountList
	 */
	public List<Account> getTransactionList() {
		return transactionList;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
