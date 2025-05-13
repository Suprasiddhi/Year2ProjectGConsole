package com.ProjectGConsole.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ProjectGConsole.model.customerModel;
import com.ProjectGConsole.service.LoginService;
import com.ProjectGConsole.util.CookieUtil;
import com.ProjectGConsole.util.SessionUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final LoginService loginService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
    	this.loginService = new LoginService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("WEB-INF/pages/loginpage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String customer_username = req.getParameter("Username");
		String customer_password = req.getParameter("Password");
		
		System.out.print(customer_username + "" + customer_password);
		customerModel customerModel = new customerModel(customer_username, customer_password);
		Boolean loginStatus = loginService.loginUser(customerModel);
		if (loginStatus != null && loginStatus) {
			System.out.println("Login successful for user: " + customer_username);
			req.getSession().setAttribute("loggedIn", true);
			SessionUtil.setAttribute(req, "username", customer_username);
			
			customerModel fullUser = loginService.getUserDetails(customer_username);
            if (fullUser != null) {
                SessionUtil.setAttribute(req, "profilePicture", fullUser.getCustomer_image() != null ? fullUser.getCustomer_image() : "ppdefault.png");
                SessionUtil.setAttribute(req, "username", fullUser.getCustomer_username());
                // You can add more if needed
            }
            
			if (customer_username.equals("admin")) {
				CookieUtil.addCookie(resp, "role", "admin", 5 * 30);
				resp.sendRedirect(req.getContextPath() + "/dashboard"); // Redirect to /home
			} else {
				CookieUtil.addCookie(resp, "role", "customer", 5 * 30);
				resp.sendRedirect(req.getContextPath() + "/logedinhome"); // Redirect to /home
			}
		} else {
			handleLoginFailure(req, resp, loginStatus);
		}
	}
	
	/**
	 * Handles login failures by setting attributes and forwarding to the login
	 * page.
	 *
	 * @param req         HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		req.setAttribute("error", errorMessage);
		req.getRequestDispatcher("/WEB-INF/pages/loginpage.jsp").forward(req, resp);
	}

}


