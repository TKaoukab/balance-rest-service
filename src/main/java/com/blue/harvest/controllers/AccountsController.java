package com.blue.harvest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blue.harvest.beans.Customer;
import com.blue.harvest.exception.Error;
import com.blue.harvest.services.AccountService;

@RestController
@RequestMapping("/customer")
public class AccountsController {

	@Autowired
	AccountService accountService;

	@GetMapping(path="/{customerId}")
	public ResponseEntity<Customer> getUser(@PathVariable String customerId) {
				
		Customer customer = accountService.findCustomerById(Integer.parseInt(customerId));

		if(customer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
		
		return new ResponseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
	}
	
	
    @ExceptionHandler({ NumberFormatException.class })
    protected ResponseEntity<Error> handleException() {
    	Error error = new Error(1,"the customer ID has an invalid value");
    	return new ResponseEntity<Error>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
