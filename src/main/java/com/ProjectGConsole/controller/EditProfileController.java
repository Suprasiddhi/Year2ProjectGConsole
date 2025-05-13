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

import com.ProjectGConsole.model.customerModel;
import com.ProjectGConsole.service.EditProfileService;
import com.ProjectGConsole.util.ImageUtil;

@WebServlet("/editprofile")
@MultipartConfig
public class EditProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EditProfileService profileService = new EditProfileService();
    private final ImageUtil imageUtil = new ImageUtil();

    public EditProfileController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            System.out.println("No session or username, redirecting to login");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String username = (String) session.getAttribute("username");
        customerModel user = profileService.getUserDetails(username);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/editprofilepage.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Processing POST request to /editprofile");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            System.out.println("No session or username, redirecting to login");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String username = (String) session.getAttribute("username");
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phonenumber = request.getParameter("phonenumber");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
            Part image = request.getPart("profileImage");

            String imageurl = null;
            if (image != null && image.getSize() > 0) {
                imageurl = imageUtil.getImageNameFromPart(image);
                System.out.println("Image URL from ImageUtil: " + imageurl);
                boolean imageUploaded = uploadImage(request);
                System.out.println("Image upload status: " + (imageUploaded ? "Success" : "Failed"));
                if (!imageUploaded) {
                    imageurl = null; // Reset if upload fails
                }
            }

            customerModel updatedCustomer = new customerModel(name, email, phonenumber, address, gender, dob, username, imageurl);
            boolean isUpdated = profileService.updateUser(updatedCustomer);

            if (isUpdated) {
                System.out.println("Profile updated successfully for username: " + username);
                session.setAttribute("profilePicture", imageurl);
                response.sendRedirect(request.getContextPath() + "/loggedinhomepage");
            } else {
                System.out.println("Failed to update profile for username: " + username);
                request.setAttribute("message", "Failed to update profile. Please try again.");
                customerModel user = profileService.getUserDetails(username);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/pages/editprofilepage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println("Error processing profile update: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            customerModel user = profileService.getUserDetails(username);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/editprofilepage.jsp").forward(request, response);
        }
    }

    private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
        Part image = req.getPart("profileImage");
        boolean result = imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "customer");
        System.out.println("ImageUtil.uploadImage result: " + result);
        return result;
    }
}