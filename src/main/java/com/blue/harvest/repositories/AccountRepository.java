package com.blue.harvest.repositories;

import com.blue.harvest.beans.Customer;

/**
 * Custom repository class
 * @author Tarik Kaoukab
 *
 */
public interface AccountRepository {
	
	/**
	 * Search a customer by Id
	 * @param customerId
	 * @return the customer corresponding to customerId
	 */
	public Customer findAccountById(int customerId);

	/**
	 * Adds a new account to the customer, with a transaction
	 * @param initialCredit
	 * @param customer
	 */
	public Customer addAccountToCustomer(Customer customer, double initialCredit);

}
