package org.smu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.smu.connection.JDBC;
import org.smu.model.Bookings;

public class JDBCBookingsDAO implements BookingsDAO{

	@Override
	public List<Bookings> getAllBookings() {
		List<Bookings> Bookings = new ArrayList<Bookings>();
		try {
			Statement statement = JDBC.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM test.Bookings");

			Bookings booking = null;
			while(resultSet.next()){
				booking= new Bookings();
				booking.setId(resultSet.getInt("id"));
				booking.setCustomerId(resultSet.getInt("customerId"));
				booking.setMovie(resultSet.getString("movie"));
				booking.setTickets(resultSet.getString("tickets"));
				booking.setDate(resultSet.getString("date"));
				booking.setTime(resultSet.getString("time"));
				
				Bookings.add(booking);
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			System.out.println(e);
		}
		return Bookings;
	}

	@Override
	public Bookings addBookings(Bookings booking) throws SQLException {

		PreparedStatement preparedStatement = null;
		String insertBookingsSQL = "INSERT INTO test.Bookings"
				+ "(customerId, movie, tickets,date,time) VALUES"
				+ "(?,?,?,?,?)";
		try {
			
			preparedStatement = JDBC.getConnection().prepareStatement(insertBookingsSQL);
			
			preparedStatement.setInt(1,booking.getCustomerId());
			preparedStatement.setString(2,booking.getMovie());
			preparedStatement.setString(3,booking.getTickets());
			preparedStatement.setString(4,booking.getDate());
			preparedStatement.setString(5,booking.getTime());
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return booking;
	}

	@Override
	public Bookings updateBookings(Bookings booking) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateBookingsSQL = "UPDATE test.Bookings SET customerId =?, movie=?, tickets=?,date=?,time=? WHERE id=?";
		try {
				preparedStatement = JDBC.getConnection().prepareStatement(updateBookingsSQL);
				
				preparedStatement.setInt(1,booking.getCustomerId());
				preparedStatement.setString(2,booking.getMovie());
				preparedStatement.setString(3,booking.getTickets());
				preparedStatement.setString(4,booking.getDate());
				preparedStatement.setString(5,booking.getTime());
				preparedStatement.setInt(6, booking.getId());

				
				preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return booking;
	}

	@Override
	public void deleteBookings(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateBookingsSQL = "DELETE from test.Bookings WHERE id=?";
		try {
				preparedStatement = JDBC.getConnection().prepareStatement(updateBookingsSQL);
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
