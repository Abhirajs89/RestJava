package org.smu.dao;

import java.sql.SQLException;
import java.util.List;

import org.smu.model.CreditCard;

public interface CreditDAO {
	
	List<CreditCard> getAllCreditCards();
	
	CreditCard addCard(CreditCard card) throws SQLException;
	
	CreditCard updateCard(CreditCard card) throws SQLException;
	
	void deleteCard(int id) throws SQLException;
	
}
