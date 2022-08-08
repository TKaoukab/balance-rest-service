package com.blue.harvest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.blue.harvest.beans.Customer;
import com.blue.harvest.repositories.AccountRepositoryImpl;
import com.blue.harvest.services.AccountService;

@SpringBootTest(classes= {ServiceAccountTest.class})
public class ServiceAccountTest {

	@Mock
	AccountRepositoryImpl accountRepository;
	
	@InjectMocks
	AccountService accountService;
	
	@Test
	public void testFindCustomerById() {
		
		HashMap<Integer, Customer> customersMap = new HashMap<>();
		Customer customer = new Customer(1, "David", "Beckham");
		customersMap.put(1, customer);
		
		accountRepository.setCustomersMap(customersMap);
		when(accountRepository.findAccountById(1)).thenReturn(customer);
		
		assertEquals(1, accountService.findCustomerById(1).getCustomerId());
	}
}
