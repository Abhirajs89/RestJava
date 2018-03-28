package org.smu.dao;

import java.sql.SQLException;
import java.util.List;

import org.smu.model.Bookings;

public interface BookingsDAO {

	List<Bookings> getAllBookings();
	
	Bookings addBookings(Bookings booking) throws SQLException;
	
	Bookings updateBookings(Bookings booking) throws SQLException;
	
	void deleteBookings(int id) throws SQLException;
}
