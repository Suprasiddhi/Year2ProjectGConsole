package com.ProjectGConsole.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
	// Database configuration information
		private static final String DB_NAME = "projectgconsole";
		private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
		private static final String USERNAME = "root";
		private static final String PASSWORD = "";
		
		/**
		 * Establishes a connection to the database 
		 * @return
		 * @throws SQLException            if a database access error occurs
		 * @throws ClassNotFoundException  if the JDBC driver class is not found
		 */
		public static Connection getDbConnection() 
				throws SQLException, ClassNotFoundException{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Successfull connected");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
}
