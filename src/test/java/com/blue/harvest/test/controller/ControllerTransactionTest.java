package com.blue.harvest.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.blue.harvest.api.beans.Customer;
import com.blue.harvest.api.controllers.TransactionController;
import com.blue.harvest.api.services.CustomerService;
import com.blue.harvest.api.services.TransactionService;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.blue.harvest")
@AutoConfigureMockMvc
@SpringBootTest(classes= {ControllerTransactionTest.class})
public class ControllerTransactionTest {

	@Autowired
	MockMvc mockMvc;
	
	@InjectMocks
	TransactionController transactionController;
	
	@Mock
	TransactionService transactionService;
	
	@Mock
	CustomerService customerService;
		
	@BeforeEach
	private void setUp() {
		 mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
	}

	
	@Test
	void testAddAccountToCustomer() throws Exception {
		
		int customerId = 1;
		Customer customer = new Customer(customerId, "David", "Beckham", null);
				
		when(customerService.findCustomerById(1)).thenReturn(customer);
		when(transactionService.addAccountToCustomer(customer, 100)).thenReturn(customer);
		
		this.mockMvc.perform(post("/account/{customerId}", customerId).queryParam("initialCredit", "100"))
		.andExpect(status().isCreated());
		
	}
}
