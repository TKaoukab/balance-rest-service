package com.blue.harvest.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.blue.harvest.beans.Customer;
import com.blue.harvest.repositories.AccountRepositoryImpl;
import com.blue.harvest.services.AccountService;

@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes = {AccountRepositoryImpl.class, AccountService.class})
@SpringBootTest(classes= {ServiceAccountTest.class})
public class ServiceAccountTest {

	@Autowired
	AccountRepositoryImpl accountRepository;
	
	@Autowired
	AccountService accountService;
	
	private Customer customer = new Customer(1, "David", "Beckham");
	
	@BeforeEach
	private void init() {
		HashMap<Integer, Customer> customersMap = new HashMap<>();
		customersMap.put(1, customer);
		accountRepository.setCustomersMap(customersMap);
	}

	
	@Test
	void testFindCustomerById() {
				
		assertEquals(1, accountService.findCustomerById(1).getCustomerId());
	}
}