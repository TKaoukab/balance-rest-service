package com.blue.harvest.repositories;

import com.blue.harvest.beans.Customer;

/**
 * Custom repository class
 * @author Tarik Kaoukab
 *
 */
public interface AccountRepository {
	
	  public Customer findAccountById(int customerId);

}
