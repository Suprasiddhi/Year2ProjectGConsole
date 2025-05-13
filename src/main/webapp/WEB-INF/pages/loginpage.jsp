<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginpage.css" />
</head>
<body>

    <div class="container">
      <div class="info-section">
        <h2>Welcome Back</h2>
        <p>Log in and continue your gaming adventure!</p>
      </div>

      <div class="form-section">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
          <input name="Username" type="text" placeholder="Username" required />
          <input name="Password" type="password" placeholder="Password" required />
          <p class="forgotpass-link">Forgot password? <a href="">Click</a></p>
          
          <button type="submit" class="login-btn">Login</button>
          <p class="extra-link">Don't have an account? <a href="register">Register</a></p>
        </form>
      </div>
    </div>
</body>
</html>
