package com.blue.harvest.front.end.controllers;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.blue.harvest.api.beans.Customer;
import com.blue.harvest.api.services.CustomerService;
import com.blue.harvest.front.end.beans.CustomerForm;

@Controller
@RequestMapping("/front/")
public class FrontEndController {

	private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

	private static final String INDEX = "index";
	private static final String CUSTOMERLIST = "customerList";

	@Autowired
	CustomerService accountService;

	@Value("${local.url}")
	private String localUrl;
	
	@GetMapping(path="/customer")
	public String  getIndex(Model model) {

		model.addAttribute("customerForm", new CustomerForm());
		return INDEX;
	}
	
	@PostMapping(path="/customer")
	public String searchCustomer(Model model, @ModelAttribute("customerForm") CustomerForm customerForm) {
				
		if(!isEntryValid(model, customerForm)) {
			return INDEX;
		}
		
		RestTemplate restTemplate = new RestTemplate();
		URL url = null;
		
		try {
			
			StringBuilder sb = new StringBuilder(localUrl).append("/customer/").append(customerForm.getCustomerId());
			
			url = new URL(sb.toString());
			
			Customer customer = restTemplate.getForObject(url.toString(), Customer.class);
			
			if(customer != null) {
				model.addAttribute("customer", customer);
				return CUSTOMERLIST;
			}
			
		}catch(MalformedURLException e) {
		
			model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Technical Error : the configured URL is not valid");
			return INDEX;
			
		}catch(Exception e) {
			if(e.getMessage() != null && e.getMessage().startsWith("404")) {
				model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Error : No account found for the ID : "+ customerForm.getCustomerId());
			}else {
				model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Technical Error : An error occured when calling the API");
			}
		
			return INDEX;
		}
		
		return INDEX;
	}
	
	@PostMapping(path="/customer/account")
	public String addAccount(Model model, @ModelAttribute("customerForm") CustomerForm customerForm) {
				
		if(!isEntryValid(model, customerForm)) {
			return INDEX;
		}
		
		try {
			
			StringBuilder sb = new StringBuilder(localUrl).append("/account/").append(customerForm.getCustomerId());
			
			URL url = new URL(sb.toString());
			
			UriComponentsBuilder uriComponentsBuilder  = UriComponentsBuilder.fromHttpUrl(url.toString());
			
			if(customerForm.getInitialCredit()!=null && !customerForm.getInitialCredit().trim().isEmpty()) {
				uriComponentsBuilder.queryParam("initialCredit", customerForm.getInitialCredit());
			}
			        
			String urlTemplate= uriComponentsBuilder.encode().toUriString();
			
			RestTemplate restTemplate = new RestTemplate();
			
			Customer customer = restTemplate.postForObject(urlTemplate, null, Customer.class);
			
			if(customer != null) {
				model.addAttribute("customer", customer);
				return CUSTOMERLIST;
			}
			
		}catch(MalformedURLException e) {
		
			model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Technical Error : the configured URL is not valid");
			return INDEX;
			
		}catch(Exception e) {
			
			model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Technical Error : An error occured when calling the API");
			return INDEX;
		}
		
		
		return CUSTOMERLIST;
	}

	/**
	 * Validates input data
	 * @param model
	 * @param customerForm
	 * @return
	 */
	private boolean isEntryValid(Model model, CustomerForm customerForm) {
		if(customerForm == null || customerForm.getCustomerId()==null) {
			model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Error : Customer ID field is mendatory");
			return false;
		}
		
		if(customerForm.getCustomerId()<=0) {
			model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Error : Customer ID must be a positive integer");
			return false;
		}
		
		if(customerForm.getInitialCredit() !=null && !customerForm.getInitialCredit().isEmpty()) {
			try {
				Double.valueOf(customerForm.getInitialCredit());
			}catch (NumberFormatException nfe) {
				model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Error : InitialCredit has an invalid value");
				return false;
			}
		}
		return true;
	}

		
}
