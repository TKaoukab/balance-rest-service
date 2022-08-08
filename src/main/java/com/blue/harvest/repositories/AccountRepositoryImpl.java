package com.blue.harvest.repositories;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.blue.harvest.beans.Customer;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
	public final String CUSTOMERSSEPARATOR = ";";
	public final String CUSTOMERFIELDSSEPARATOR = "\\|";
	public final int NUMBEROFFIELDS = 3;
	
	@Value("${customer.list}")
	private String configCustomers;
	
	private HashMap<Integer, Customer> customersMap;
	
	public Customer findAccountById(int customerId) {
		  
		if(customersMap== null || customersMap.isEmpty()) initConfCustomers();	  
		  
		return customersMap.get(customerId);
	}

	/**
	 * Reads the application.properties file and extracts the customers.
	 * @return Map of customers
	 */
	private HashMap<Integer, Customer> initConfCustomers(){
		customersMap = new HashMap<>();
		  
		if(configCustomers!=null) {
			String[] customers = configCustomers.split(CUSTOMERSSEPARATOR);
			
			for(String customer : customers) {
				String[] fields = customer.split(CUSTOMERFIELDSSEPARATOR);
				
				if(fields.length == NUMBEROFFIELDS) {
					Customer customerTmp = new Customer(Integer.valueOf(fields[0]), fields[1], fields[2]);
					customersMap.put(Integer.valueOf(fields[0]), customerTmp);
				}
			}
		}
		return customersMap;
	}

	/**
	 * @return the customersMap
	 */
	public HashMap<Integer, Customer> getCustomersMap() {
		return customersMap;
	}

	/**
	 * @param customersMap the customersMap to set
	 */
	public void setCustomersMap(HashMap<Integer, Customer> customersMap) {
		this.customersMap = customersMap;
	}
}
