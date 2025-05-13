package com.ProjectGConsole.service;

import com.ProjectGConsole.config.DbConfig;
import com.ProjectGConsole.model.customerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCustomerService {

    public boolean addCustomer(customerModel customer) {
        String query = "INSERT INTO customer (customer_username, customer_name, customer_email, customer_phonenumber, customer_gender, customer_dob, customer_address, customer_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getCustomer_username());
            stmt.setString(2, customer.getCustomer_name());
            stmt.setString(3, customer.getCustomer_email());
            stmt.setString(4, customer.getCustomer_phonenumber());
            stmt.setString(5, customer.getCustomer_gender());
            stmt.setString(6, customer.getCustomer_dob());
            stmt.setString(7, customer.getCustomer_address());
            stmt.setString(8, customer.getCustomer_image());
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error adding customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<customerModel> getAllCustomers() {
        List<customerModel> customers = new ArrayList<>();
        String query = "SELECT customer_username, customer_name, customer_email, customer_phonenumber, customer_gender, customer_dob, customer_address, customer_image FROM customer";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(new customerModel(
                    rs.getString("customer_name"),
                    rs.getString("customer_email"),
                    rs.getString("customer_phonenumber"),
                    rs.getString("customer_address"),
                    rs.getString("customer_gender"),
                    rs.getString("customer_dob"),
                    rs.getString("customer_username"),
                    rs.getString("customer_image")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
            e.printStackTrace();
        }
        return customers;
    }

    public boolean updateCustomer(customerModel customer) {
        String query = "UPDATE customer SET customer_name = ?, customer_email = ?, customer_phonenumber = ?, customer_gender = ?, customer_dob = ?, customer_address = ? WHERE customer_username = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getCustomer_name());
            stmt.setString(2, customer.getCustomer_email());
            stmt.setString(3, customer.getCustomer_phonenumber());
            stmt.setString(4, customer.getCustomer_gender());
            stmt.setString(5, customer.getCustomer_dob());
            stmt.setString(6, customer.getCustomer_address());
            stmt.setString(7, customer.getCustomer_username());
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error updating customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(String customerUsername) {
        String query = "DELETE FROM customer WHERE customer_username = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customerUsername);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}