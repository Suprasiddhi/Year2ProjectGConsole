package com.ProjectGConsole.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import com.ProjectGConsole.config.DbConfig;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DashboardController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalUsers = 0;
        int totalProductsInStock = 0;
        int totalOrdersInSale = 0;
        int totalPendingOrders = 0;
        int totalDeliveredOrders = 0;
        int totalMaleCustomers = 0;
        int totalFemaleCustomers = 0;
        String pendingOrdersError = null;
        String deliveredOrdersError = null;
        String maleCustomersError = null;
        String femaleCustomersError = null;

        try (Connection conn = DbConfig.getDbConnection()) {
            // Get total users
            String userSql = "SELECT COUNT(*) FROM customer";
            try (PreparedStatement psUser = conn.prepareStatement(userSql);
                 ResultSet rsUser = psUser.executeQuery()) {
                if (rsUser.next()) {
                    totalUsers = rsUser.getInt(1);
                }
            }

            // Get total product quantity
            String productSql = "SELECT SUM(product_stock) FROM product";
            try (PreparedStatement psProduct = conn.prepareStatement(productSql);
                 ResultSet rsProduct = psProduct.executeQuery()) {
                if (rsProduct.next()) {
                    totalProductsInStock = rsProduct.getInt(1);
                }
            }

            // Get total order count
            String orderSql = "SELECT COUNT(*) FROM `order`";
            try (PreparedStatement psOrder = conn.prepareStatement(orderSql);
                 ResultSet rsOrder = psOrder.executeQuery()) {
                if (rsOrder.next()) {
                    totalOrdersInSale = rsOrder.getInt(1);
                }
            }

            // Get total pending orders
            try {
                String pendingOrderSql = "SELECT COUNT(*) FROM `order` WHERE order_status = ?";
                System.out.println("Executing pending order query: " + pendingOrderSql + " with parameter: 0");
                try (PreparedStatement psPendingOrder = conn.prepareStatement(pendingOrderSql)) {
                    psPendingOrder.setInt(1, 0); // 0 for pending
                    try (ResultSet rsPendingOrder = psPendingOrder.executeQuery()) {
                        if (rsPendingOrder.next()) {
                            totalPendingOrders = rsPendingOrder.getInt(1);
                            System.out.println("Total pending orders (order_status = 0): " + totalPendingOrders);
                        } else {
                            System.out.println("No result returned for pending orders query.");
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving pending orders: " + e.getMessage());
                e.printStackTrace();
                totalPendingOrders = -1;
                pendingOrdersError = "Unable to load pending orders data due to a database issue.";
            }

            // Get total delivered orders
            try {
                String deliveredOrderSql = "SELECT COUNT(*) FROM `order` WHERE order_status = ?";
                System.out.println("Executing delivered order query: " + deliveredOrderSql + " with parameter: 1");
                try (PreparedStatement psDeliveredOrder = conn.prepareStatement(deliveredOrderSql)) {
                    psDeliveredOrder.setInt(1, 1); // 1 for delivered
                    try (ResultSet rsDeliveredOrder = psDeliveredOrder.executeQuery()) {
                        if (rsDeliveredOrder.next()) {
                            totalDeliveredOrders = rsDeliveredOrder.getInt(1);
                            System.out.println("Total delivered orders (order_status = 1): " + totalDeliveredOrders);
                        } else {
                            System.out.println("No result returned for delivered orders query.");
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving delivered orders: " + e.getMessage());
                e.printStackTrace();
                totalDeliveredOrders = -1;
                deliveredOrdersError = "Unable to load delivered orders data due to a database issue.";
            }

            // Get total male customers
            try {
                String maleCustomerSql = "SELECT COUNT(*) FROM customer WHERE customer_gender = ?";
                System.out.println("Executing male customer query: " + maleCustomerSql + " with parameter: Male");
                try (PreparedStatement psMaleCustomer = conn.prepareStatement(maleCustomerSql)) {
                    psMaleCustomer.setString(1, "Male");
                    try (ResultSet rsMaleCustomer = psMaleCustomer.executeQuery()) {
                        if (rsMaleCustomer.next()) {
                            totalMaleCustomers = rsMaleCustomer.getInt(1);
                            System.out.println("Total male customers: " + totalMaleCustomers);
                        } else {
                            System.out.println("No result returned for male customers query.");
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving male customers: " + e.getMessage());
                e.printStackTrace();
                totalMaleCustomers = -1;
                maleCustomersError = "Unable to load male customers data due to a database issue.";
            }

            // Get total female customers
            try {
                String femaleCustomerSql = "SELECT COUNT(*) FROM customer WHERE customer_gender = ?";
                System.out.println("Executing female customer query: " + femaleCustomerSql + " with parameter: Female");
                try (PreparedStatement psFemaleCustomer = conn.prepareStatement(femaleCustomerSql)) {
                    psFemaleCustomer.setString(1, "Female");
                    try (ResultSet rsFemaleCustomer = psFemaleCustomer.executeQuery()) {
                        if (rsFemaleCustomer.next()) {
                            totalFemaleCustomers = rsFemaleCustomer.getInt(1);
                            System.out.println("Total female customers: " + totalFemaleCustomers);
                        } else {
                            System.out.println("No result returned for female customers query.");
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving female customers: " + e.getMessage());
                e.printStackTrace();
                totalFemaleCustomers = -1;
                femaleCustomersError = "Unable to load female customers data due to a database issue.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error retrieving dashboard data: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Failed to load dashboard data. Please try again later.");
        }

        // Pass data to JSP
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalProducts", totalProductsInStock);
        request.setAttribute("totalOrders", totalOrdersInSale);
        request.setAttribute("totalPendingOrders", totalPendingOrders);
        request.setAttribute("totalDeliveredOrders", totalDeliveredOrders);
        request.setAttribute("totalMaleCustomers", totalMaleCustomers);
        request.setAttribute("totalFemaleCustomers", totalFemaleCustomers);
        if (pendingOrdersError != null) {
            request.setAttribute("pendingOrdersError", pendingOrdersError);
        }
        if (deliveredOrdersError != null) {
            request.setAttribute("deliveredOrdersError", deliveredOrdersError);
        }
        if (maleCustomersError != null) {
            request.setAttribute("maleCustomersError", maleCustomersError);
        }
        if (femaleCustomersError != null) {
            request.setAttribute("femaleCustomersError", femaleCustomersError);
        }
        request.getRequestDispatcher("/WEB-INF/pages/admindashboardpage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}