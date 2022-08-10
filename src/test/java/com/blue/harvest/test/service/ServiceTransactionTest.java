package com.blue.harvest.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.blue.harvest.api.beans.Customer;
import com.blue.harvest.api.repositories.AccountRepositoryImpl;
import com.blue.harvest.api.services.TransactionService;

@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes = {AccountRepositoryImpl.class, TransactionService.class})
@SpringBootTest(classes= {ServiceTransactionTest.class})
public class ServiceTransactionTest {

	@Autowired
	AccountRepositoryImpl accountRepository;
	
	@Autowired
	TransactionService transactionService;
	
	private static Customer customer = new Customer(1, "David", "Beckham", null);
	
	
	@BeforeEach
	void init() {
		HashMap<Integer, Customer> customersMap = new HashMap<>();
		customersMap.put(1, customer);
		accountRepository.setCustomersMap(customersMap);
	}
	
	
	@Test
	@Order(1)
	void testCustomerBalance() {
		
		assertEquals(100.20, transactionService.addAccountToCustomer(customer, 100.20).getBalance());
	}
	
	
	@Test
	@Order(2)
	void testCustomerAccounts() {
	//the size is expected to be "2" since the first assertion in the customer class variable was made in @testCustomerBalance() method above. 
	assertEquals(2, transactionService.addAccountToCustomer(customer, 10).getAccountList().size());
		
	}
	
}
