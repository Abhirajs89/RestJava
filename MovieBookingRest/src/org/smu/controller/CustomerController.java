package org.smu.controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.smu.dao.CustomerDAO;
import org.smu.dao.JDBCCustomerDAO;
import org.smu.model.Customer;

@Path("/Customers")
public class CustomerController {

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Customer> getCustomers() {
    	CustomerDAO dao = new JDBCCustomerDAO();
        List<Customer> listOfCustomers = dao.getAllCustomers();
        return listOfCustomers;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    public Customer addCustomer(Customer cust) {
    	CustomerDAO dao = new JDBCCustomerDAO();
        Customer customer = null;
		try {
			customer = dao.addCustomer(cust);
		} catch (SQLException e) {
			System.out.println("Could not insert Customer into database"+e);
		}
        return customer;
    }
}
