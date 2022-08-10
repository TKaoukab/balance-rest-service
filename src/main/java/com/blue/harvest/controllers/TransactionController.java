package com.blue.harvest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blue.harvest.beans.Customer;
import com.blue.harvest.beans.Error;
import com.blue.harvest.exception.BusinessException;
import com.blue.harvest.services.CustomerService;
import com.blue.harvest.services.TransactionService;

@RestController
@RequestMapping("/account")
public class TransactionController {

	@Autowired
	CustomerService customerService;

	@Autowired
	TransactionService transactionService;

	@PostMapping(path="/{customerId}")
	public ResponseEntity<Customer> addAccountToCustomer(@PathVariable String customerId, @RequestParam("initialCredit") String initialCredit) throws BusinessException {
		
		Customer customer = null;
		
		try {
			
			customer = customerService.findCustomerById(Integer.parseInt(customerId));
			
		}catch(NumberFormatException nfe) {
			
			throw new BusinessException(1, "the customer ID has an invalid value");
		}
		
		if(customer != null) {
			
			try {
				
				transactionService.addAccountToCustomer(customer, Double.parseDouble(initialCredit));
			
			}catch(NumberFormatException nfe) {
				
				throw new BusinessException(2, "the initialCredit has an invalid value");
			}	
				
			return new ResponseEntity<>(customer, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
	}
	
	
    @ExceptionHandler({ BusinessException.class })
    protected ResponseEntity<Error> handleBusinessException(BusinessException be) {

    	return new ResponseEntity<>(be.getError(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
