package com.blue.harvest.api.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blue.harvest.api.beans.Account;
import com.blue.harvest.api.beans.Customer;
import com.blue.harvest.api.repositories.AccountRepository;

@Component
@Service
public class CustomerService {

	@Autowired
	AccountRepository accountRepository;
	
	/**
	 * Search a customer by Id
	 * @param customerId
	 * @return the customer corresponding to customerId
	 */
	public Customer findCustomerById(int customerId) {
		
		Customer customer = accountRepository.findAccountById(customerId);
		
		if(customer != null && customer.getAccountList()!=null) {
			
			calculateBalance(customer);
		}
		return customer;
	}

	/**
	 * Iterates over the customer's transactions and calculate the balance.
	 * @param customer
	 */
	private void calculateBalance(Customer customer) {
		if(customer.getAccountList()!=null && !customer.getAccountList().isEmpty()) {
			double balance = 0;
			for(Account account: customer.getAccountList()) {
				if(account.getTransaction() != null) {
					balance += account.getTransaction();
				}
			}
			//ensure that the resulting double follows the specified format : #.####
			customer.setBalance(BigDecimal.valueOf(balance).setScale(4, RoundingMode.HALF_UP).doubleValue());
		}
	}
}
