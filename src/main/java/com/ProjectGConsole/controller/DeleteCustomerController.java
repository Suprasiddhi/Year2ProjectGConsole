package com.ProjectGConsole.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.ProjectGConsole.model.customerModel;
import com.ProjectGConsole.service.AddCustomerService;

@WebServlet("/deletecustomer")
public class DeleteCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            String customerUsername = request.getParameter("customerUsername");
            AddCustomerService service = new AddCustomerService();
            boolean deleted = service.deleteCustomer(customerUsername);

            List<customerModel> customers = service.getAllCustomers();
            request.setAttribute("customers", customers);

            if (deleted) {
                request.setAttribute("success", "Customer deleted successfully!");
            } else {
                request.setAttribute("error", "Could not delete customer. Please try again.");
            }

            request.getRequestDispatcher("/WEB-INF/pages/adminuserpage.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            AddCustomerService service = new AddCustomerService();
            request.setAttribute("customers", service.getAllCustomers());
            request.getRequestDispatcher("/WEB-INF/pages/adminuserpage.jsp").forward(request, response);
        }
    }
}