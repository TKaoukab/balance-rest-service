package com.blue.harvest.beans;

/**
 * This class handle the transaction of an account
 * @implNote this implementation handles ONE transaction only for each account. Handling a list of transactions requires some changes in code (using @List) and iterating over this list.
 * @author Tarik Kaoukab
 *
 */
public class Account {

	private int accountId;
	
	private Double transaction;

	
	/**
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the transaction
	 */
	public Double getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(double transaction) {
		this.transaction = Double.valueOf(transaction);
	}

	@Override
	public String toString() {
		return "Account [transaction=" + transaction + "]";
	}
	
}
