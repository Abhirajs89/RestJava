package org.smu.controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.smu.dao.CustomerDAO;
import org.smu.dao.JDBCCustomerDAO;
import org.smu.model.Customer;
import org.smu.model.Error;

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
    public Response addCustomer(Customer cust) {
    	CustomerDAO dao = new JDBCCustomerDAO();
		try {
			 dao.addCustomer(cust);
		} catch (SQLException e) {
			System.out.println("ERROR while inserting Customer"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Produces({ MediaType.APPLICATION_JSON})
    public Response updateCustomer(Customer cust) {
    	if(cust.getId()==0) {
    		Error error = new Error();
    		error.setErrorMsg("Customer ID field required to Update Record");
    		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    	}
    	CustomerDAO dao = new JDBCCustomerDAO();
		try {
			 dao.updateCustomer(cust);
		} catch (SQLException e) {
			System.out.println("ERROR while updating Customer"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.CREATED).build();
    }
}
