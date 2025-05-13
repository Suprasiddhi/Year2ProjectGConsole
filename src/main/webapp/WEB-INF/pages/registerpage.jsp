<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Register</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerpage.css" />
</head>
<body>
  <div class="container">
    <div class="info-section">
      <h2>Join Us</h2>
      <p>Be part of our gaming community to enjoy exclusive benefits and stay updated!</p>
    </div>
        
    <div class="form-section">
      <h2>Register</h2>
      
		<% String error = (String) request.getAttribute("error");
           if (error != null) { %>
           <p style="color: red;"><%= error %></p>
        <% } %>
        <% String success = (String) request.getAttribute("success");
           if (success != null) { %>
           <p style="color: green;"><%= success %></p>
        <% } %>
		
      <form action="${pageContext.request.contextPath}/register" enctype="multipart/form-data" method="post">
        <input name="Name" type="text" placeholder="Name" required />
        <input name="Email" type="email" placeholder="Email" required />
        <input name="Phone Number" type="tel" placeholder="Phone Number" required />
        <input name="Address" type="text" placeholder="Address" required />
        
        <select name="Gender" required>
          <option value="" disabled selected>Select Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
        </select>
       
        <input name="Dob" type="date" placeholder="Dob" required />
        <input name="Username" type="text" placeholder="Username" required />
        <input name="Password" type="password" placeholder="Password" required />
        <input name="Re-type Password" type="password" placeholder="Re-type Password" required />
        
        <label for="Image">Choose a Profile Image:</label>
        <input type="file" id="Image" name="Image" accept="image/*" />
        
        <button type="submit" class="register-btn">Register</button>
      </form>
    </div>
  </div>
</body>
</html>
