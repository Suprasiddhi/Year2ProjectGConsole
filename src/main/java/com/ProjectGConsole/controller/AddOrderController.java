package com.ProjectGConsole.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.ProjectGConsole.model.orderModel;
import com.ProjectGConsole.service.AddOrderService;

@MultipartConfig
@WebServlet("/addorder")
public class AddOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddOrderController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet called for /addorder");
        AddOrderService service = new AddOrderService();
        List<orderModel> orders = service.getAllOrders();
        List<String> customerUsernames = service.getCustomerUsernames();
        request.setAttribute("orders", orders);
        request.setAttribute("customerUsernames", customerUsernames);
        request.getRequestDispatcher("/WEB-INF/pages/adminorderpage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called for /addorder");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedIn") == null) {
            System.out.println("Redirecting to login due to invalid session");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        System.out.println("Session valid, processing form data");
        try {
            orderModel order = extractOrderModel(request);
            System.out.println("Order extracted: ID=" + order.getOrder_id() + ", Customer=" + order.getCustomer_username() +
                              ", Quantity=" + order.getOrder_quantity() + ", Price=" + order.getOrder_price() +
                              ", DeliveryType=" + order.getOrder_deliverytype() + ", Status=" + order.getOrder_status());
            AddOrderService service = new AddOrderService();
            boolean isCreated = service.AddOrder(order);
            if (isCreated) {
                List<orderModel> orders = service.getAllOrders();
                List<String> customerUsernames = service.getCustomerUsernames();
                request.setAttribute("orders", orders);
                request.setAttribute("customerUsernames", customerUsernames);
                handleSuccess(request, response, "Order added successfully!", "/WEB-INF/pages/adminorderpage.jsp");
            } else {
                handleError(request, response, "Could not add order. Possible causes: duplicate order ID, invalid customer username, or invalid data.");
            }
        } catch (ServletException e) {
            System.err.println("ServletException in doPost: " + e.getMessage());
            e.printStackTrace();
            handleError(request, response, "Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error in doPost: " + e.getMessage());
            e.printStackTrace();
            handleError(request, response, "Failed to add order due to server error: " + e.getMessage());
        }
    }

    private orderModel extractOrderModel(HttpServletRequest req) throws IOException, ServletException {
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int price = Integer.parseInt(req.getParameter("price"));
            String deliveryType = req.getParameter("deliveryType");
            String status = req.getParameter("status");
            String customerUsername = req.getParameter("customerUsername");
            int statusInt = "Pending".equals(status) ? 0 : "Delivered".equals(status) ? 1 : 2;
            return new orderModel(orderId, customerUsername, quantity, price, deliveryType, statusInt);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid numeric input: Order ID, quantity, or price must be valid numbers", e);
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        System.out.println("Error Called: " + message);
        req.setAttribute("error", message);
        AddOrderService service = new AddOrderService();
        List<orderModel> orders = service.getAllOrders();
        List<String> customerUsernames = service.getCustomerUsernames();
        req.setAttribute("orders", orders);
        req.setAttribute("customerUsernames", customerUsernames);
        req.getRequestDispatcher("/WEB-INF/pages/adminorderpage.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        System.out.println("Success Called: " + message);
        req.setAttribute("success", message);
        AddOrderService service = new AddOrderService();
        List<orderModel> orders = service.getAllOrders();
        List<String> customerUsernames = service.getCustomerUsernames();
        req.setAttribute("orders", orders);
        req.setAttribute("customerUsernames", customerUsernames);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }
}