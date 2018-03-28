package org.smu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.smu.connection.JDBC;
import org.smu.model.CreditCard;

public class JDBCCreditDAO implements CreditDAO{

	@Override
	public List<CreditCard> getAllCreditCards() {
		List<CreditCard> cards = new ArrayList<CreditCard>();
		try {
			Statement statement = JDBC.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM test.CreditCard");

			CreditCard card = null;
			while(resultSet.next()){
				card = new CreditCard();
				card.setId(resultSet.getInt("id"));
				card.setCustomerID(resultSet.getInt("CustomerId"));
				card.setName(resultSet.getString("Name"));
				card.setCardType(resultSet.getString("CardType"));
				card.setCardNumber(resultSet.getString("CardNumber"));
				card.setPIN(resultSet.getString("PIN"));
				card.setExpiry(resultSet.getString("Expiry"));

				cards.add(card);
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			System.out.println(e);
		}
		return cards;
	}

	@Override
	public CreditCard addCard(CreditCard card) throws SQLException {

		PreparedStatement preparedStatement = null;
		String insertCardSQL = "INSERT INTO test.Customer"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
		try {
			
			preparedStatement = JDBC.getConnection().prepareStatement(insertCardSQL);
			
			preparedStatement.setInt(1,card.getId());
			preparedStatement.setInt(2,card.getCustomerID());
			preparedStatement.setString(3,card.getName());
			preparedStatement.setString(4,card.getCardType());
			preparedStatement.setString(5,card.getCardNumber());
			preparedStatement.setString(6,card.getPIN());
			preparedStatement.setString(7,card.getExpiry());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return card;
	}

	@Override
	public CreditCard updateCard(CreditCard card) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateCustomerSQL = "UPDATE test.Customer SET firstName =?, lastName=?, city=?,province=?,country=?,postal=?,phone=?,email=?,address=? WHERE id=?";
		try {
				preparedStatement = JDBC.getConnection().prepareStatement(updateCustomerSQL);
				
				preparedStatement.setInt(1,card.getId());
				preparedStatement.setInt(2,card.getCustomerID());
				preparedStatement.setString(3,card.getName());
				preparedStatement.setString(4,card.getCardType());
				preparedStatement.setString(5,card.getCardNumber());
				preparedStatement.setString(6,card.getPIN());
				preparedStatement.setString(7,card.getExpiry());
				
				preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return card;
	}

	@Override
	public void deleteCard(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateCardSQL = "DELETE from test.CreditCard WHERE id=?";
		try {
				preparedStatement = JDBC.getConnection().prepareStatement(updateCardSQL);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}

}
