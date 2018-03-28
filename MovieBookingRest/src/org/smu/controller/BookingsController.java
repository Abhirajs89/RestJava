package org.smu.controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.smu.dao.BookingsDAO;
import org.smu.dao.JDBCBookingsDAO;
import org.smu.model.Bookings;
import org.smu.model.Error;

@Path("/Bookings")
public class BookingsController {

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getBookings() {
    	BookingsDAO dao = new JDBCBookingsDAO();
        List<Bookings> listOfBookings = dao.getAllBookings();
        if(listOfBookings.size() ==0) {
        	Error error = new Error();
    		error.setErrorMsg("No records found in the database");
        	return Response.status(Response.Status.NO_CONTENT).entity(error).build();
        }
        return Response.status(Response.Status.OK).entity(listOfBookings).build();
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    public Response addBookings(Bookings booking) {
    	BookingsDAO dao = new JDBCBookingsDAO();
		try {
			 dao.addBookings(booking);
		} catch (SQLException e) {
			System.out.println("ERROR while inserting Booking information"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Produces({ MediaType.APPLICATION_JSON})
    public Response updateBookings(Bookings booking) {
    	if(booking.getId()==0) {
    		Error error = new Error();
    		error.setErrorMsg("ID field required to Update Record");
    		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    	}
    	BookingsDAO dao = new JDBCBookingsDAO();
		try {
			 dao.updateBookings(booking);
		} catch (SQLException e) {
			System.out.println("ERROR while updating Booking"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response deleteBookings(@PathParam("id") int id) {
    	if(id == 0) {
    		Error error = new Error();
    		error.setErrorMsg("ID field required to Update Record");
    		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    	}
    	BookingsDAO dao = new JDBCBookingsDAO();
		try {
			 dao.deleteBookings(id);
		} catch (SQLException e) {
			System.out.println("ERROR while deleting Booking information"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
    }
}
