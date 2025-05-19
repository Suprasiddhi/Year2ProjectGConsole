package com.ProjectGConsole.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.ProjectGConsole.util.CookieUtil;
import com.ProjectGConsole.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

	private static final String LOGIN = "/login";
	private static final String REGISTER = "/register";
	private static final String HOME = "/home";
	private static final String LOGEDINHOME = "/logedinhome";
	private static final String ROOT = "/";
	private static final String DASHBOARD = "/dashboard";
	private static final String ADMINPRODUCT = "/adminproduct";
	private static final String ADDPRODUCT = "/addproduct";
	private static final String SUPPORT = "/support";
	private static final String CART = "/cart";
	private static final String DEVICE = "/device";
	private static final String EDITPROFILE = "/editprofile";
	private static final String LOGOUT = "/logout";
	private static final String DELETEPRODUCT ="/deleteproduct";
	private static final String EDITPRODUCT ="/editproduct";
	private static final String ADDCUSTOMER ="/addcustomer";
	private static final String EDITCUSTOMER="/editcustomer";
	private static final String DELETECUSTOMER="/deletecustomer";
	private static final String ADDORDER ="/addorder";
	private static final String ABOUTUS = "/aboutus";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization logic, if required
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		
		// Allow access to resources
		if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css")) {
			chain.doFilter(request, response);
			return;
		}
		
		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
		String userRole = CookieUtil.getCookie(req, "role") != null ? CookieUtil.getCookie(req, "role").getValue()
				: null;

		if ("admin".equals(userRole)) {
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                res.sendRedirect(req.getContextPath() + DASHBOARD);
            } else if (
                    uri.endsWith(DASHBOARD) || uri.endsWith(HOME) || uri.endsWith(ROOT)
                    || uri.endsWith(LOGEDINHOME) || uri.endsWith(SUPPORT)
                    || uri.endsWith(CART) || uri.endsWith(DEVICE) || uri.endsWith(EDITPROFILE) || uri.endsWith(LOGOUT)
                    || uri.endsWith(ADMINPRODUCT) || uri.endsWith(ADDPRODUCT) || uri.endsWith(DELETEPRODUCT)
                    || uri.endsWith(EDITPRODUCT) || uri.endsWith(ADDCUSTOMER) || uri.endsWith(EDITCUSTOMER)
                    || uri.endsWith(DELETECUSTOMER) || uri.endsWith(ADDORDER) || uri.endsWith(ABOUTUS) )
             {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + DASHBOARD);
            }
        } else if ("customer".equals(userRole)) {
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                res.sendRedirect(req.getContextPath() + LOGEDINHOME);
            } else if (
                    uri.endsWith(HOME) || uri.endsWith(LOGEDINHOME) || uri.endsWith(ROOT) || uri.endsWith(CART)
                    || uri.endsWith(DEVICE) || uri.endsWith(SUPPORT) || uri.endsWith(EDITPROFILE) || uri.endsWith(LOGOUT)
                    || uri.endsWith(ABOUTUS) 
            ) {
                chain.doFilter(request, response);
            } else if (uri.endsWith(DASHBOARD)){
                res.sendRedirect(req.getContextPath() + LOGEDINHOME);
            } else {
                res.sendRedirect(req.getContextPath() + LOGEDINHOME);
            }
        } else {
            // Not logged in
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME)
                    || uri.endsWith(ROOT)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + LOGIN);
            }
        }
    }
	@Override
	public void destroy() {
		// Cleanup logic, if required
	}
}