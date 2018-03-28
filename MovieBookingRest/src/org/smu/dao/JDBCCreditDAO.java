package org.smu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.smu.connection.JDBC;
import org.smu.model.CreditCard;
import org.smu.model.Customer;

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
		String insertCustomerSQL = "INSERT INTO test.Customer"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
		try {
			
			preparedStatement = JDBC.getConnection().prepareStatement(insertCustomerSQL);
			
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
	public Customer updateCustomer(Customer cust) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateCustomerSQL = "UPDATE test.Customer SET firstName =?, lastName=?, city=?,province=?,country=?,postal=?,phone=?,email=?,address=? WHERE id=?";
		try {
				preparedStatement = JDBC.getConnection().prepareStatement(updateCustomerSQL);
				
				preparedStatement.setString(1,cust.getFirstName());
				preparedStatement.setString(2,cust.getLastName());
				preparedStatement.setString(3,cust.getCity());
				preparedStatement.setString(4,cust.getProvince());
				preparedStatement.setString(5,cust.getCountry());
				preparedStatement.setString(6,cust.getPostal());
				preparedStatement.setString(7,cust.getPhone());
				preparedStatement.setString(8,cust.getEmail());
				preparedStatement.setString(9,cust.getAddress());
				preparedStatement.setInt(10, cust.getId());
				
				preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return cust;
	}

	@Override
	public void deleteCustomer(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateCustomerSQL = "DELETE from test.Customer WHERE id=?";
		try {
				preparedStatement = JDBC.getConnection().prepareStatement(updateCustomerSQL);
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
