package com.blue.harvest.beans;

public class Account {

	private double transaction;

	public Account(double transaction) {
		this.transaction = transaction;
	}
	
	/**
	 * @return the transaction
	 */
	public double getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(double transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Account [transaction=" + transaction + "]";
	}
	
}
