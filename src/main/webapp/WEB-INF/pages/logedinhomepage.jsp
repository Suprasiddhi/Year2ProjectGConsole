<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProjectGConsole Home Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/logedinhomepage.css" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="slideshow-container">
        <div class="slide fade">
            <img src="${pageContext.request.contextPath}/resources/images/customer/playstation.png" alt="Slide 1">
        </div>
        <div class="slide fade">
            <img src="${pageContext.request.contextPath}/resources/images/customer/xbox.png" alt="Slide 2">
        </div>
        <div class="slide fade">
            <img src="${pageContext.request.contextPath}/resources/images/customer/nintendo.png" alt="Slide 3">
        </div>
    </div>
    <script>
        let slideIndex = 0;
        showSlides();
    
        function showSlides() {
            let i;
            const slides = document.getElementsByClassName("slide");
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";  
            }
            slideIndex++;
            if (slideIndex > slides.length) {slideIndex = 1}    
            slides[slideIndex-1].style.display = "block";  
            setTimeout(showSlides, 4000); // Change image every 4 seconds
        }
    </script>

<div class="trending-container">
    <div class="text-section">
        <h1><b>Trending:</b><br>Discover all PS5<br>Consoles</h1>
        <h2>PS5 BUNDLE</h2>
        <p class="description">
            Play PS5® games with the most impressive visuals ever possible<br>
            on a PlayStation console.
        </p>
        <div class="buttons">
            <button class="view-product">View Product</button>
            <button class="buy-now">Buy now</button>
        </div>
        <div class="price">
            Price: <span>$599.99</span>
        </div>
    </div>
    <div class="image-section">
        <img src="${pageContext.request.contextPath}/resources/images/customer/ps5bundle.png" alt="PS5 HD Camera">
    </div>
</div>

</body>
</html>

<section class="product-grid-section">
    <div class="product-grid">
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/ps5.png" alt="Device 1">
            <h4>Playstation 5</h4>
            <p class="price">$399.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/ps4.png" alt="Device 2">
            <h4>Playstation 4</h4>
            <p class="price">$299.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/psvr.png" alt="Device 3">
            <h4>Playstation VR</h4>
            <p class="price">$59.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/ps3.png" alt="Device 4">
            <h4>Playstation 3</h4>
            <p class="price">$199.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
    </div>
    <div class="product-grid">
        
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/xboxnextgen.png" alt="Device 1">
            <h4>Xbox Next Gen Bundle:</h4>
            <p class="price">$699.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/xboxx.png" alt="Device 2">
            <h4>Xbox Next Gen</h4>
            <p class="price">$599.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/xboxones.png" alt="Device 3">
            <h4>Xbox One X</h4>
            <p class="price">$299.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/xboxonex.png" alt="Device 4">
            <h4>Xbox One X</h4>
            <p class="price">$299.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
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