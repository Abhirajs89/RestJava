package org.smu.dao;

import java.sql.SQLException;
import java.util.List;

import org.smu.model.Customer;

public interface CustomerDAO {

	List<Customer> getAllCustomers();
	
	Customer addCustomer(Customer cust) throws SQLException;
	
	Customer updateCustomer(Customer cust) throws SQLException;
	
	void deleteCustomer(int id) throws SQLException;
}
