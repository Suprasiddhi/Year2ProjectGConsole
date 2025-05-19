<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
</head>
<body>

<header class="header">
<div class="container header-content">
            <h1 class="logo">ProjectGConsole</h1>
            <nav class="nav">
                <a href="logedinhome">Home</a>
                <a href="device">Devices</a>
                <a href="cart">Cart</a>
                <a href="support">Support</a>
                <a href="aboutus">About Us</a>
            </nav>
<% if (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn")) { %>
        
            <div class="profile-dropdown">
                <img src="${pageContext.request.contextPath}/resources/images/customer/${empty sessionScope.profilePicture ? 'ppdefault.png' : sessionScope.profilePicture}" alt="Profile" class="profile-pic" />
                <span class="username">${sessionScope.username}</span>
                <div class="dropdown-content">
                    <a href="editprofile">Edit Profile</a>
                    <a href="logout">Logout</a>
                </div>
            </div>
        
    <% } else { %>
            <div class="auth-buttons">
                <a href="login"><button class="login">Login</button></a>
                <a href="register"><button class="sign-in">Sign Up</button></a>
            </div>
        </div>
       <% } %>
    </header>

</body>
</html>