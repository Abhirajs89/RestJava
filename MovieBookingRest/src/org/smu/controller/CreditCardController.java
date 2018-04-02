package org.smu.controller;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;

import org.glassfish.json.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.smu.dao.CreditDAO;
import org.smu.dao.JDBCCreditDAO;
import org.smu.model.CreditCard;
import org.smu.model.Error;

@Path("/CreditCard")
public class CreditCardController {
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getCards() {
    	CreditDAO dao = new JDBCCreditDAO();
        List<CreditCard> listOfCards = dao.getAllCreditCards();
        if(listOfCards.size() ==0) {
        	Error error = new Error();
    		error.setErrorMsg("No records found in the database");
        	return Response.status(Response.Status.NO_CONTENT).entity(error).build();
        }
        return Response.status(Response.Status.OK).entity(listOfCards).build();
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    public Response addCard(String data) {
    	CreditDAO dao = new JDBCCreditDAO();
		try {

			System.out.println(data);
			CreditCard card = new CreditCard();
			JsonReader reader = Json.createReader(new StringReader(data));
			JsonObject cardObject = reader.readObject();
			reader.close();
			card.setCardNumber(cardObject.getString("CardNumber"));
			card.setCardType(cardObject.getString("CardType"));
			card.setCustomerID(cardObject.getInt("CustomerId"));
			card.setExpiry(cardObject.getString("ExpiryDate"));
			card.setName(cardObject.getString("name"));
			card.setSecurityPin(cardObject.getString("securityPin"));
			
//			System.out.println("Card ID is "+card.getCustomerId());
//			System.out.println("Card Type is "+card.getCardType());
			
			dao.addCard(card);
		} catch (SQLException e) {
			System.out.println("ERROR while inserting Credit Card"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Produces({ MediaType.APPLICATION_JSON})
    public Response updateCard(String data) {
    	CreditCard card = new CreditCard();
		JsonReader reader = Json.createReader(new StringReader(data));
		JsonObject cardObject = reader.readObject();
		reader.close();
		card.setId(cardObject.getInt("ID"));
		card.setCardNumber(cardObject.getString("CardNumber"));
		card.setCardType(cardObject.getString("CardType"));
		card.setCustomerID(cardObject.getInt("CustomerId"));
		card.setExpiry(cardObject.getString("ExpiryDate"));
		card.setName(cardObject.getString("name"));
		card.setSecurityPin(cardObject.getString("securityPin"));
    	System.out.println(card.getId());
    	if(card.getId()==0) {
    		Error error = new Error();
    		error.setErrorMsg("Credit ID field required to Update Record");
    		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    	}
    	CreditDAO dao = new JDBCCreditDAO();
		try {
			 dao.updateCard(card);
		} catch (SQLException e) {
			System.out.println("ERROR while updating Customer"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response deleteCard(@PathParam("id") int id) {
    	if(id == 0) {
    		Error error = new Error();
    		error.setErrorMsg("Customer ID field required to Update Record");
    		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    	}
    	CreditDAO dao = new JDBCCreditDAO();
		try {
			 dao.deleteCard(id);
		} catch (SQLException e) {
			System.out.println("ERROR while deleting Customer"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
    }
}
