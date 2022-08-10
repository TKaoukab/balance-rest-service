package com.blue.harvest.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.blue.harvest.beans.Customer;
import com.blue.harvest.controllers.CustomerController;
import com.blue.harvest.services.CustomerService;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.blue.harvest")
@AutoConfigureMockMvc
@SpringBootTest(classes= {ControllerAccountTest.class})
public class ControllerAccountTest {

	@Autowired
	MockMvc mockMvc;
	
	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService;
	
	
	@BeforeEach
	private void setUp() {
		 mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	
	@Test
	void testGetCustomerById() throws Exception {
		
		int customerId = 1;
		Customer customer = new Customer(customerId, "David", "Beckham", null);

		when(customerService.findCustomerById(1)).thenReturn(customer);
		
		this.mockMvc.perform(get("/customer/{customerId}", customerId))
		.andExpect(status().isOk());
		
	}
	
}
