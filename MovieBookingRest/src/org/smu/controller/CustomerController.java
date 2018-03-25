package org.smu.controller;

import java.util.List;

import javax.ws.rs.GET;
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
    public List<Customer> getEmployees_JSON() {
    	CustomerDAO dao = new JDBCCustomerDAO();
        List<Customer> listOfCustomers = dao.getAllCustomers();
        return listOfCustomers;
    }
}
