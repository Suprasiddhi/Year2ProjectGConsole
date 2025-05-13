package com.ProjectGConsole.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

import com.ProjectGConsole.model.customerModel;
import com.ProjectGConsole.service.AddCustomerService;

@MultipartConfig
@WebServlet("/addcustomer")
public class AddCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddCustomerService service = new AddCustomerService();
        List<customerModel> customers = service.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/WEB-INF/pages/adminuserpage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            String username = request.getParameter("customerUsername");
            String name = request.getParameter("customerName");
            String email = request.getParameter("customerEmail");
            String phonenumber = request.getParameter("customerPhonenumber");
            String gender = request.getParameter("customerGender");
            String dob = request.getParameter("customerDob");
            String address = request.getParameter("customerAddress");
            Part filePart = request.getPart("customerImage");
            String imagePath = filePart != null && filePart.getSize() > 0 ? "uploads/" + filePart.getSubmittedFileName() : null;

            customerModel customer = new customerModel(name, email, phonenumber, address, gender, dob, username, imagePath);
            AddCustomerService service = new AddCustomerService();
            boolean added = service.addCustomer(customer);

            List<customerModel> customers = service.getAllCustomers();
            request.setAttribute("customers", customers);

            if (added) {
                request.setAttribute("success", "Customer added successfully!");
            } else {
                request.setAttribute("error", "Could not add customer. Please try again.");
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