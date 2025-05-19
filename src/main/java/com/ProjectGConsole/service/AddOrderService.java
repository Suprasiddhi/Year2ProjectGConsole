package com.ProjectGConsole.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ProjectGConsole.config.DbConfig;
import com.ProjectGConsole.model.orderModel;

public class AddOrderService {

    public Boolean AddOrder(orderModel order) {
        try (Connection conn = DbConfig.getDbConnection()) {
            // Validate customer_username exists
            String validateSql = "SELECT COUNT(*) FROM customer WHERE customer_username = ?";
            try (PreparedStatement validateStmt = conn.prepareStatement(validateSql)) {
                validateStmt.setString(1, order.getCustomer_username());
                try (ResultSet rs = validateStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        System.err.println("Invalid customer_username: " + order.getCustomer_username());
                        return false;
                    }
                }
            }

            // Insert order
            String insertSql = "INSERT INTO `order` (order_id, customer_username, order_quantity, order_price, order_deliverytype, order_status) " +
                             "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setInt(1, order.getOrder_id());
                insertStmt.setString(2, order.getCustomer_username());
                insertStmt.setInt(3, order.getOrder_quantity());
                insertStmt.setInt(4, order.getOrder_price());
                insertStmt.setString(5, order.getOrder_deliverytype());
                insertStmt.setInt(6, order.getOrder_status());
                System.out.println("Executing SQL: " + insertStmt.toString());
                int rowsAffected = insertStmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("SQL Error during adding order: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<orderModel> getAllOrders() {
        List<orderModel> orders = new ArrayList<>();
        String query = "SELECT order_id, customer_username, order_quantity, order_price, order_deliverytype, order_status FROM `order`";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int quantity = rs.getInt("order_quantity");
                int price = rs.getInt("order_price");
                String deliverytype = rs.getString("order_deliverytype");
                int status = rs.getInt("order_status");
                String customerUsername = rs.getString("customer_username");
                orders.add(new orderModel(orderId, customerUsername, quantity, price, deliverytype, status));
            }
            System.out.println("Retrieved " + orders.size() + " orders from database");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error retrieving orders: " + e.getMessage());
            e.printStackTrace();
        }
        return orders;
    }

    public List<String> getCustomerUsernames() {
        List<String> usernames = new ArrayList<>();
        String query = "SELECT customer_username FROM customer ORDER BY customer_username";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usernames.add(rs.getString("customer_username"));
            }
            System.out.println("Retrieved " + usernames.size() + " customer usernames");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error retrieving customer usernames: " + e.getMessage());
            e.printStackTrace();
        }
        return usernames;
    }
}