package com.ProjectGConsole.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ProjectGConsole.config.DbConfig;
import com.ProjectGConsole.model.productModel;

public class AddProductService {

    public Boolean AddProduct(productModel product) {
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement insertStmt = conn.prepareStatement(
                 "INSERT INTO product(product_id, product_name, product_stock, product_price, product_quantity) "
                 + "VALUES (?, ?, ?, ?, ?)")) {
            insertStmt.setInt(1, product.getProduct_id());
            insertStmt.setString(2, product.getProduct_name());
            insertStmt.setInt(3, product.getProduct_stock());
            insertStmt.setInt(4, product.getProduct_price());
            insertStmt.setInt(5, product.getProduct_quantity());
            System.out.println("Executing SQL: " + insertStmt.toString());
            return insertStmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error during product adding: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<productModel> getAllProducts() {
        List<productModel> products = new ArrayList<>();
        String query = "SELECT product_id, product_name, product_stock, product_price, product_quantity FROM product";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int stock = rs.getInt("product_stock");
                int price = rs.getInt("product_price");
                int quantity = rs.getInt("product_quantity");
                products.add(new productModel(productId, productName, stock, price, quantity));
            }
            System.out.println("Retrieved " + products.size() + " products from database");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    public boolean updateProduct(productModel product) {
        String query = "UPDATE product SET product_name = ?, product_stock = ?, product_price = ?, product_quantity = ? WHERE product_id = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getProduct_name());
            stmt.setInt(2, product.getProduct_stock());
            stmt.setInt(3, product.getProduct_price());
            stmt.setInt(4, product.getProduct_quantity());
            stmt.setInt(5, product.getProduct_id());
            System.out.println("Executing SQL: " + stmt.toString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error updating product: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM product WHERE product_id = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            System.out.println("Executing SQL: " + stmt.toString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error deleting product: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}