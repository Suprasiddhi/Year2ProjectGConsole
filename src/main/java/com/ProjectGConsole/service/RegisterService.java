package com.ProjectGConsole.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ProjectGConsole.config.DbConfig;
import com.ProjectGConsole.model.customerModel;

public class RegisterService {
	
	private Connection dbConn; 
	
	public RegisterService() {
		try { 
		this.dbConn=DbConfig.getDbConnection(); //Connecting database
		}
		catch(SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public Boolean addCustomer(customerModel customerModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}
		
		String insertQuery = "INSERT INTO customer (customer_name,customer_email,customer_phonenumber,customer_address,customer_gender,customer_dob,customer_username,customer_password,customer_image)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
			
			// Insert customer details
						insertStmt.setString(1, customerModel.getCustomer_name());
						insertStmt.setString(2, customerModel.getCustomer_email());
						insertStmt.setString(3, customerModel.getCustomer_phonenumber());
						insertStmt.setString(4, customerModel.getCustomer_address());
						insertStmt.setString(5, customerModel.getCustomer_gender());
						insertStmt.setString(6, customerModel.getCustomer_dob());
						insertStmt.setString(7, customerModel.getCustomer_username());
						insertStmt.setString(8, customerModel.getCustomer_password());
						insertStmt.setString(9, customerModel.getCustomer_image());


						return insertStmt.executeUpdate() > 0;
					} catch (SQLException e) {
						System.err.println("Error during customer registration: " + e.getMessage());
						e.printStackTrace();
						return null;
					}
		}
	}

