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

import com.ProjectGConsole.model.productModel;
import com.ProjectGConsole.service.AddProductService;

@MultipartConfig
@WebServlet("/addproduct")
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddProductController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet called for /addproduct");
        AddProductService service = new AddProductService();
        List<productModel> products = service.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/pages/adminproductpage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called for /addproduct");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedIn") == null) {
            System.out.println("Redirecting to login due to invalid session");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        System.out.println("Session valid, processing form data");
        try {
            productModel product = extractProductModel(request);
            System.out.println("Product extracted: " + product.getProduct_name());
            AddProductService service = new AddProductService();
            Boolean isCreated = service.AddProduct(product);
            if (isCreated == null) {
                handleError(request, response, "Our server is under maintenance");
            } else if (isCreated) {
                // Refresh product list after successful addition
                List<productModel> products = service.getAllProducts();
                request.setAttribute("products", products);
                handleSuccess(request, response, "Product added successfully!", "/WEB-INF/pages/adminproductpage.jsp");
            } else {
                handleError(request, response, "Could not add product. Please try again later!");
            }
        } catch (Exception e) {
            System.err.println("Error in doPost: " + e.getMessage());
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred. Please try again later!");
        }
    }

    private productModel extractProductModel(HttpServletRequest req) throws IOException, ServletException {
        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            String productName = req.getParameter("productName");
            String stockStr = req.getParameter("stock");
            int stock = "Yes".equals(stockStr) ? 1 : 0;
            int price = Integer.parseInt(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            return new productModel(productId, productName, stock, price, quantity);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid input: Numeric fields must be valid numbers", e);
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        System.out.println("Error Called: " + message);
        req.setAttribute("error", message);
        // Refresh product list even on error to ensure table is populated
        AddProductService service = new AddProductService();
        List<productModel> products = service.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/pages/adminproductpage.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        System.out.println("Success Called: " + message);
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }
}