package com.blue.harvest.api.repositories;

import com.blue.harvest.api.beans.Customer;

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

}
