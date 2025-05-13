<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProjectGConsole Home Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartpage.css">
</head>
<body>
    <jsp:include page="header.jsp" />


    <section class="product-grid-section">
        <h1><b>Your Cart</b></h1>
        <div class="product-grid">
          <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/ps5.png" alt="PS5 Console">
            <h4>Playstation 5</h4>
            <p class="price">$399.99</p>
            <div class="product-actions">
              <button class="cart-btn">Remove</button>
            </div>
          </div>
          <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/xboxnextgen.png" alt="Xbox Controller">
            <h4>Xbox Next Gen</h4>
            <p class="price">$599.99</p>
            <div class="product-actions">
              <button class="cart-btn">Remove</button>
            </div>
          </div>
        </div>
        <div style="text-align: center; margin-top: 2rem;">
            <button class="see-more">Add more</button>
          </div>
        <div style="text-align: center; margin-top: 2rem;">
          <button class="see-more">Proceed to Checkout</button>
        </div>
      </section>

    <footer class="footer">
        <div class="map-container">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3532.3118409609833!2d85.32266147546738!3d27.70765647618234!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb1908434cb3c5%3A0x1fdf1a6d41d2512d!2sIslington%20College!5e0!3m2!1sen!2snp!4v1745459810277!5m2!1sen!2snp" 
            width="100%" 
            height="250" 
            style="border:0;" 
            allowfullscreen="" 
            loading="lazy" 
            referrerpolicy="no-referrer-when-downgrade">
        </iframe>
        </div>
        <div class="container footer-grid">
            <div class="footer-section">
                <h3>About</h3>
                <a href="#">About Us</a>
                <a href="#">Careers</a>
                <a href="#">Studios</a>
                <a href="#">History</a>
            </div>
            <div class="footer-section">
                <h3>Products</h3>
                <a href="#">Consoles</a>
                <a href="#">Accessories</a>
                <a href="#">Plus</a>
            </div>
            <div class="footer-section">
                <h3>Support</h3>
                <a href="#">Help Center</a>
                <a href="#">Repair</a>
                <a href="#">Reset Password</a>
            </div>
            <div class="footer-section">
                <h3>Connect</h3>
                <a href="#">Facebook</a>
                <a href="#">Instagram</a>
                <a href="#">YouTube</a>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 Project Gaming Console LLC. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>




