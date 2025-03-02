package com.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.emp.connection.DbConnection;


public class EmpDao {

	// when the Employee logins in isValid method authenticates the employee using the name and password
	public boolean isValid(String name,String password) throws ClassNotFoundException, SQLException
	{
		// Establishing the connection
		Connection connection = DbConnection.getConnection();
		// Query for whether employee credentials are there in table
		String query="select * from Employee where name=? and password=?";
		// Creating the prepared statement object
		PreparedStatement statement = connection.prepareStatement(query);
		// Setting query parameters
		statement.setString(1,name);
		statement.setString(2,password);
		// result set for storing the result obtained against the query
		ResultSet result = statement.executeQuery();
		System.out.println("Query executed with: Name = " + name + ", Password = " + password);

		    if (result.next()) {
		        System.out.println("User Found");
		        return true;
		    } else {
		        System.out.println("User Not Found");
		        return false;
		    }
		
	}
	
	public boolean authenticate(int id, String name, String department, String email) throws ClassNotFoundException, SQLException
	{
		// Establishing the connection
	    Connection connection = DbConnection.getConnection();
	    // query to check user existence
	    String query = "SELECT * FROM Employee WHERE id = ? AND name = ? AND department = ? AND email = ?";
	    // Creating the prepared statement object
	    PreparedStatement statement = connection.prepareStatement(query);
	    // Setting query parameters
	    statement.setInt(1, id);
	    statement.setString(2, name);
	    statement.setString(3, department);
	    statement.setString(4, email);
	    
	    // result set for storing the result obtained against the query
	    ResultSet result = statement.executeQuery();
	    System.out.println("Query executed with: ID = " + id + ", Name = " + name + ", Department = " + department + ", Email = " + email);
	    
	    // Checking if user exists
	    if (result.next()) {
	        System.out.println("User Found");
	        return true;
	    } else {
	        System.out.println("User Not Found");
	        return false;
	    }
	}

	
	public boolean addEmployee(EmpRegisterDao emp) throws ClassNotFoundException, SQLException
	{
		// Establishing the connection
		Connection connection = DbConnection.getConnection();
		// query for insertion
		String query="Insert into Employee values (?,?,?,?,?)";
		// Creating the prepared statement object
		PreparedStatement statement = connection.prepareStatement(query);
	    // Setting query parameters
		statement.setInt(1,emp.getId());
		statement.setString(2,emp.getName());
		statement.setString(3,emp.getPassword());
		statement.setString(4,emp.getDepartment());
		statement.setString(5,emp.getEmail());
		// statement returns the integer value(number of rows affected)
		int result = statement.executeUpdate();
		return result>0;
	}
}
