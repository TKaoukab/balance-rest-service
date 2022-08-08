package com.blue.harvest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blue.harvest.beans.Account;
import com.blue.harvest.beans.Customer;
import com.blue.harvest.repositories.AccountRepository;

@Component
@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public Customer findCustomerById(int customerId) {
		
		Customer customer = accountRepository.findAccountById(customerId);
		
		if(customer != null && customer.getTransactionList()!=null) {
			
			calculateBalance(customer);
		}
		return customer;
	}

	/**
	 * Iterates over the customer's transactions and calculate the balance.
	 * @param customer
	 */
	private void calculateBalance(Customer customer) {
		if(customer.getTransactionList()!=null && !customer.getTransactionList().isEmpty()) {
			double balance = 0;
			for(Account account: customer.getTransactionList()) {
				balance += account.getTransaction();
			}
			customer.setBalance(balance);
		}
	}
}
