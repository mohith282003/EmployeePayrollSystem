package com.emp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	// url , userName , password are required for establishing the connection
	private static String url="jdbc:mysql://localhost:3306/employeepayrollsystem";
	private static String userName = "root";
	private static String password  = "Mohith@$2003";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		// Loading and registering the Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Establishing the connection 
		return DriverManager.getConnection(url, userName, password);
	}
}
