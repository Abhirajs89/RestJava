package org.smu.dao;

import java.util.List;

import org.smu.model.Customer;

public interface CustomerDAO {

	List<Customer> getAllCustomers();
	
	Customer addCustomer(Customer cust);
	
	Customer updateCustomer(Customer cust);
	
	void deleteCustomer(int id);
}
