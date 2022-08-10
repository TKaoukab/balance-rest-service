package com.blue.harvest.api.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blue.harvest.api.beans.Account;
import com.blue.harvest.api.beans.Customer;
import com.blue.harvest.api.repositories.AccountRepository;

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
			
		Account account = new Account();
		
		if(customer.getAccountList() == null) {
			customer.setAccountList(new ArrayList<>());
		}
		
		account.setAccountId(customer.getAccountList().size()+1);
		
		//if initialCredit is not 0, a transaction will be sent to the new account
		if(initialCredit != 0) {
			//ensure that the resulting double follows the specified format : #.####
			account.setTransaction(BigDecimal.valueOf(initialCredit).setScale(4, RoundingMode.HALF_UP).doubleValue());
		}
		
		customer.getAccountList().add(account);


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
