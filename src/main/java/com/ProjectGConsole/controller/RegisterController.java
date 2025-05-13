package com.ProjectGConsole.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.ProjectGConsole.model.customerModel;
import com.ProjectGConsole.service.RegisterService;
import com.ProjectGConsole.util.PasswordUtil;
import com.ProjectGConsole.util.ValidationUtil;
import com.ProjectGConsole.util.ImageUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
				maxFileSize = 1024 * 1024 * 10, // 10MB
				maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ImageUtil imageUtil = new ImageUtil();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("WEB-INF/pages/registerpage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// Validate and extract customer model
			String validationMessage = validateRegistrationForm(req);
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
			}

			customerModel customerModel = extractCustomerModel(req);
			RegisterService registerService = new RegisterService();
			Boolean isAdded = registerService.addCustomer(customerModel);

			if (isAdded == null) {
				handleError(req, resp, "Our server is under maintenance. Please try again later!");
			} 
			else if (isAdded){
				try {
					if (uploadImage(req)) {
						handleSuccess(req,resp,"Account is successfully created!","/WEB-INF/pages/loginpage.jsp");;
					} else {
						handleError(req, resp, "Could not upload the image. Please try again later!");
					}
				} catch (IOException | ServletException e) {
					handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
					e.printStackTrace(); // Log the exception
				}
				
				
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}
	
	private String validateRegistrationForm(HttpServletRequest req) {
		String customer_name = req.getParameter("Name");
		String customer_email = req.getParameter("Email");
		String customer_phonenumber = req.getParameter("Phone Number");
		String customer_address = req.getParameter("Address");
		String customer_gender = req.getParameter("Gender");
		String customer_dobStr = req.getParameter("Dob");
		String customer_username = req.getParameter("Username");
		String customer_password = req.getParameter("Password");
		String customer_retypePassword = req.getParameter("Re-type Password");

		// Check for null or empty fields first
		if (ValidationUtil.isNullOrEmpty(customer_name))
			return "Full name is required.";
		if (ValidationUtil.isNullOrEmpty(customer_email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(customer_phonenumber))
			return "Phone Number is required.";
		if (ValidationUtil.isNullOrEmpty(customer_address))
			return "Address is required.";
		if (ValidationUtil.isNullOrEmpty(customer_gender))
			return "Gender is required.";
		if (ValidationUtil.isNullOrEmpty(customer_dobStr))
			return "Date of birth is required.";
		if (ValidationUtil.isNullOrEmpty(customer_username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(customer_password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(customer_retypePassword))
			return "Please retype the password.";

		// Convert date of birth
		LocalDate dob;
		try {
			dob = LocalDate.parse(customer_dobStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		// Validate fields
		if (!ValidationUtil.isValidEmail(customer_email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(customer_phonenumber))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.isValidGender(customer_gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isAlphanumericStartingWithLetter(customer_username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidPassword(customer_password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidationUtil.doPasswordsMatch(customer_password, customer_retypePassword))
			return "Passwords do not match.";

		// Check if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast16(dob))
		return "You must be at least 16 years old to register.";
		
		try {
			Part image = req.getPart("Image");
			if (!ValidationUtil.isValidImageExtension(image))
				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
		} catch (IOException | ServletException e) {
			return "Error handling image file. Please ensure the file is valid.";
		}
		
		return null; // All validations passed
	}
	
	private customerModel extractCustomerModel(HttpServletRequest req) throws Exception {
		String customer_name = req.getParameter("Name");
		String customer_email = req.getParameter("Email");
		String customer_phonenumber = req.getParameter("Phone Number");
		String customer_address = req.getParameter("Address");
		String customer_gender = req.getParameter("Gender");
		String customer_dob = req.getParameter("Dob");
		String customer_username = req.getParameter("Username");
		String customer_password = req.getParameter("Password");
	
		Part image = req.getPart("Image");
		String customer_image = imageUtil.getImageNameFromPart(image);

		// Assuming password validation is already done in validateRegistrationForm
		customer_password = PasswordUtil.encrypt(customer_username, customer_password);

		return new customerModel(customer_name, customer_email, customer_phonenumber, customer_address, customer_gender, customer_dob,customer_username,customer_password,customer_image);
	}
	
	private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("Image");
		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "customer");
	}
	
	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("Name", req.getParameter("Name"));
		req.setAttribute("Email", req.getParameter("Email"));
		req.setAttribute("Phone Number", req.getParameter("Phone Number"));
		req.setAttribute("Address", req.getParameter("Address"));
		req.setAttribute("Gender", req.getParameter("Gender"));
		req.setAttribute("Dob", req.getParameter("Dob"));
		req.setAttribute("Username", req.getParameter("Username"));
		
		req.getRequestDispatcher("/WEB-INF/pages/registerpage.jsp").forward(req, resp);
	}
}


