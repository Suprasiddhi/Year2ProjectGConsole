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

@WebServlet("/editproduct")
public class EditProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called for /editproduct");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedIn") == null) {
            System.out.println("Redirecting to login due to invalid session");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            int productId = Integer.parseInt(request.getParameter("editProductId"));
            String productName = request.getParameter("editProductName");
            String stockStr = request.getParameter("editStock");
            int stock = "Yes".equals(stockStr) ? 1 : 0;
            int quantity = Integer.parseInt(request.getParameter("editQuantity"));
            int price = Integer.parseInt(request.getParameter("editPrice"));

            productModel product = new productModel(productId, productName, stock, price, quantity);
            AddProductService service = new AddProductService();
            boolean updated = service.updateProduct(product);

            List<productModel> products = service.getAllProducts();
            System.out.println("Setting products attribute with " + products.size() + " items after edit");
            request.setAttribute("products", products);

            if (updated) {
                request.setAttribute("success", "Product updated successfully!");
            } else {
                request.setAttribute("error", "Could not update product. Please try again.");
            }

            request.getRequestDispatcher("/WEB-INF/pages/adminproductpage.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: " + e.getMessage());
            request.setAttribute("error", "Invalid input: Numeric fields must be valid numbers.");
            AddProductService service = new AddProductService();
            request.setAttribute("products", service.getAllProducts());
            request.getRequestDispatcher("/WEB-INF/pages/adminproductpage.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Error in doPost: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred while updating the product.");
            AddProductService service = new AddProductService();
            request.setAttribute("products", service.getAllProducts());
            request.getRequestDispatcher("/WEB-INF/pages/adminproductpage.jsp").forward(request, response);
        }
    }
}