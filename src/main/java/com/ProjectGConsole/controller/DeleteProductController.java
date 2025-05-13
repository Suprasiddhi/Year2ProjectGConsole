package com.ProjectGConsole.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.ProjectGConsole.model.productModel;
import com.ProjectGConsole.service.AddProductService;

@WebServlet("/deleteproduct")
public class DeleteProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called for /deleteproduct");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedIn") == null) {
            System.out.println("Redirecting to login due to invalid session");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            AddProductService service = new AddProductService();
            boolean deleted = service.deleteProduct(productId);

            List<productModel> products = service.getAllProducts();
            System.out.println("Setting products attribute with " + products.size() + " items after delete");
            request.setAttribute("products", products);

            if (deleted) {
                request.setAttribute("success", "Product deleted successfully!");
            } else {
                request.setAttribute("error", "Could not delete product. Please try again.");
            }

            request.getRequestDispatcher("/WEB-INF/pages/adminproductpage.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.err.println("Invalid product ID: " + e.getMessage());
            request.setAttribute("error", "Invalid product ID.");
            AddProductService service = new AddProductService();
            request.setAttribute("products", service.getAllProducts());
            request.getRequestDispatcher("/WEB-INF/pages/adminproductpage.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Error in doPost: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred while deleting the product.");
            AddProductService service = new AddProductService();
            request.setAttribute("products", service.getAllProducts());
            request.getRequestDispatcher("/WEB-INF/pages/adminproductpage.jsp").forward(request, response);
        }
    }
}