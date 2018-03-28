package org.smu.dao;

import java.sql.SQLException;
import java.util.List;

import org.smu.model.CreditCard;
import org.smu.model.Customer;

public interface CreditDAO {
	
	List<CreditCard> getAllCreditCards();
	
	Customer addCard(CreditCard card) throws SQLException;
	
	Customer updateCard(CreditCard card) throws SQLException;
	
	void deleteCard(int id) throws SQLException;
	
}
