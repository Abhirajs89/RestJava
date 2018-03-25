package org.smu.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

	private JDBC() {

	}

	private static Connection connection = null;

	public static Connection getConnection(){
		if(connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				if(connection == null)
					connection = DriverManager.getConnection("jdbc:mysql://cs.smu.ca:3306/test","mcda551001","eve3afet");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
