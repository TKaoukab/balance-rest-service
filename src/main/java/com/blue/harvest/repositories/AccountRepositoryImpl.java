package com.blue.harvest.repositories;

import java.util.HashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.blue.harvest.beans.Customer;

@Repository
public class AccountRepositoryImpl implements AccountRepository, InitializingBean {
	
	public static final String CUSTOMERSSEPARATOR = ";";
	public static final String CUSTOMERFIELDSSEPARATOR = "\\|";
	public static final int NUMBEROFFIELDS = 3;
	
	@Value("${customer.list}")
	private String configCustomers;
	
	private HashMap<Integer, Customer> customersMap;
	
	
	public void afterPropertiesSet() throws Exception{
		
		if(customersMap== null || customersMap.isEmpty()) 
			initConfCustomers();
		
	}
	
	
	public Customer findAccountById(int customerId) {
		
		//customersMap is never null here, because it is initialized in the @afterPropertiesSet() method above.
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
					Customer customerTmp = new Customer(Integer.valueOf(fields[0]), fields[1], fields[2], null);
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
