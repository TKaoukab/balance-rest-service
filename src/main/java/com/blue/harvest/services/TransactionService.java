package com.blue.harvest.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blue.harvest.beans.Account;
import com.blue.harvest.beans.Customer;
import com.blue.harvest.repositories.AccountRepository;

@Component
@Service
public class TransactionService {

	@Autowired
	AccountRepository accountRepository;
	
	/**
	 * opens a new current account for already existing customers, and calculates the customer's balance.
	 * @param customer
	 * @param initialCredit
	 * @return 
	 * @return the customer corresponding to customerId
	 */
	public Customer addAccountToCustomer(Customer customer, double initialCredit) {
		
		accountRepository.addAccountToCustomer(customer, initialCredit);

		return calculateBalance(customer);
		
	}

	/**
	 * Iterates over the customer's transactions and calculate the balance.
	 * @param customer
	 */
	private Customer calculateBalance(Customer customer) {
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
		
		return customer;
	}
}
