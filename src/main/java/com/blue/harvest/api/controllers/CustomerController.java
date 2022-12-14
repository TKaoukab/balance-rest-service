package com.blue.harvest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blue.harvest.api.beans.Customer;
import com.blue.harvest.api.exception.BusinessException;
import com.blue.harvest.api.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService accountService;

	@GetMapping(path="/{customerId}")
	public ResponseEntity<Customer> getUser(@PathVariable String customerId) throws BusinessException {
				
		Customer customer = null;
		
		try {
			
			customer = accountService.findCustomerById(Integer.parseInt(customerId));
			
		}catch(NumberFormatException nfe) {
			throw new BusinessException(1, "the customer ID has an invalid value");
		}

		if(customer != null) {
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}

		return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
	}
	
	
    @ExceptionHandler({ BusinessException.class })
    protected ResponseEntity<BusinessException> handleBusinessException(BusinessException be) {

    	return new ResponseEntity<>(be, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
