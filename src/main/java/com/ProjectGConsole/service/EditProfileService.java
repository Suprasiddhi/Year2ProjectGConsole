package com.ProjectGConsole.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ProjectGConsole.config.DbConfig;
import com.ProjectGConsole.model.customerModel;

public class EditProfileService {
    private Connection conn;
    private boolean isConnectionError = false;

    public EditProfileService() {
        try {
            this.conn = DbConfig.getDbConnection();
            System.out.println("Database connection established");
        } catch (Exception e) {
            isConnectionError = true;
            System.err.println("Failed to establish database connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public customerModel getUserDetails(String username) {
        if (isConnectionError) {
            System.out.println("Database connection error. Unable to fetch user details.");
            return null;
        }

        String query = "SELECT customer_name, customer_email, customer_phonenumber, customer_address, customer_gender, customer_dob, customer_image FROM customer WHERE customer_username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                customerModel user = new customerModel();
                user.setCustomer_name(result.getString("customer_name"));
                user.setCustomer_email(result.getString("customer_email"));
                user.setCustomer_phonenumber(result.getString("customer_phonenumber"));
                user.setCustomer_address(result.getString("customer_address"));
                user.setCustomer_gender(result.getString("customer_gender"));
                String dob = result.getString("customer_dob");
                user.setCustomer_dob(dob != null ? dob : "");
                user.setCustomer_image(result.getString("customer_image"));
                System.out.println("Fetched user details for username: " + username);
                return user;
            } else {
                System.out.println("No user details found for username: " + username);
            }
        } catch (SQLException e) {
            System.err.println("Database error while fetching user details: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateUser(customerModel user) {
        if (isConnectionError || conn == null) {
            System.out.println("Database connection is null or failed.");
            return false;
        }

        String updateQuery = "UPDATE customer SET customer_name = ?, customer_email = ?, customer_phonenumber = ?, customer_address = ?, customer_gender = ?, customer_dob = ?, customer_image = ? WHERE customer_username = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setString(1, user.getCustomer_name());
            updateStmt.setString(2, user.getCustomer_email());
            updateStmt.setString(3, user.getCustomer_phonenumber());
            updateStmt.setString(4, user.getCustomer_address());
            updateStmt.setString(5, user.getCustomer_gender());
            String dob = user.getCustomer_dob();
            if (dob != null && !dob.isEmpty()) {
                updateStmt.setString(6, dob); // Assumes VARCHAR(10) in YYYY-MM-DD
            } else {
                updateStmt.setNull(6, java.sql.Types.VARCHAR);
            }
            updateStmt.setString(7, user.getCustomer_image());
            updateStmt.setString(8, user.getCustomer_username());

            int rowsAffected = updateStmt.executeUpdate();
            System.out.println("Update user rows affected: " + rowsAffected + " for username: " + user.getCustomer_username());
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error during user update: Code=" + e.getErrorCode() + ", Message=" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}