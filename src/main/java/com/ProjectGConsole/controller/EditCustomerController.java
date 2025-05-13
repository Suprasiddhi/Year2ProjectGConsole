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

@WebServlet("/editcustomer")
public class EditCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            String username = request.getParameter("editCustomerUsername");
            String name = request.getParameter("editCustomerName");
            String email = request.getParameter("editCustomerEmail");
            String phonenumber = request.getParameter("editCustomerPhonenumber");
            String gender = request.getParameter("editCustomerGender");
            String dob = request.getParameter("editCustomerDob");
            String address = request.getParameter("editCustomerAddress");

            customerModel customer = new customerModel(name, email, phonenumber, address, gender, dob, username, null);
            AddCustomerService service = new AddCustomerService();
            boolean updated = service.updateCustomer(customer);

            List<customerModel> customers = service.getAllCustomers();
            request.setAttribute("customers", customers);

            if (updated) {
                request.setAttribute("success", "Customer updated successfully!");
            } else {
                request.setAttribute("error", "Could not update customer. Please try again.");
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