package com.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.emp.connection.DbConnection;

public class AdminDao {

	// when the Admin logins in isValid method authenticates the admin using the name and password
	public boolean isValid(String name,String password) throws ClassNotFoundException, SQLException
	{
		// Establishing the connection
		Connection connection = DbConnection.getConnection();
		// Query for whether admin credentials are there in table 
		String query="select * from Admin where name=? and password=?";
		// Creating the prepared statement object
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1,name);
		statement.setString(2,password);
		// result set for storing the result obtained against the query
		ResultSet result = statement.executeQuery();
		System.out.println("Query executed with: Name = " + name + ", Password = " + password);

		    if (result.next()) {
		        System.out.println("Admin Found");
		        return true;
		    } else {
		        System.out.println("Admin Not Found");
		        return false;
		    }
		
	}
}
