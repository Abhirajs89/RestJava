package org.smu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.smu.connection.JDBC;
import org.smu.model.Customer;

public class JDBCCustomerDAO implements CustomerDAO{

	@Override
	public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        try {
               Statement statement = JDBC.getConnection().createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM test.Customer");
                
               Customer customer = null;
               while(resultSet.next()){
               	customer = new Customer();
               	customer.setId(resultSet.getInt("id"));
               	customer.setFirstName(resultSet.getString("firstname"));
               	customer.setLastName(resultSet.getString("lastName"));
               	customer.setCity(resultSet.getString("city"));
               	customer.setProvince(resultSet.getString("province"));
               	customer.setCountry(resultSet.getString("country"));
               	customer.setPostal(resultSet.getString("postal"));
               	customer.setPhone(resultSet.getString("phone"));
               	customer.setEmail(resultSet.getString("email"));
               	customer.setAddress(resultSet.getString("address"));
                
               	customers.add(customer);
               }
               resultSet.close();
               statement.close();
                
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return customers;
	}

	@Override
	public Customer addCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		
	}

}
