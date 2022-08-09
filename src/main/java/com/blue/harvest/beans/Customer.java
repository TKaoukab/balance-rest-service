package com.blue.harvest.beans;

import java.util.List;

public class Customer {

	private int customerId;
	
	private String name;
	
	private String surname;
	
	private double balance;

	private List<Account> accountList;
	
	public Customer() {}
	
	public Customer(int customerId, String name, String surname) {
		this.customerId = customerId;
		this.name = name;
		this.surname = surname;
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
	public List<Account> getAccountList() {
		return accountList;
	}
	
	/**
	 * @param the accountTransactionList
	 */
	public void setAccountList(List<Account> accountAccountList) {
		this.accountList = accountAccountList;
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
