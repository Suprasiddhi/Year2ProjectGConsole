<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProjectGConsole Home Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/devicepage.css">
</head>
<body>
    <jsp:include page="header.jsp" />

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
    <h1><b>Playstation:</b></h1>
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
    <div class="see-more-btn">
        <button class="see-more">See more</button>
    </div>
    <h1><b>XBOX:</b></h1>
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
    <div class="see-more-btn">
        <button class="see-more">See more</button>
    </div>
    <h1><b>Nintendo:</b></h1>
    <div class="product-grid">
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/nintendobundle.png" alt="Device 1">
            <h4>Nintendo Mario Bundle</h4>
            <p class="price">$399.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/nintendoes.png" alt="Device 2">
            <h4>Nintendo Entertainment System</h4>
            <p class="price">$199.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/nintendoswitch.png" alt="Device 3">
            <h4>Nintendo Switch</h4>
            <p class="price">$159.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/customer/nintendo64.png" alt="Device 4">
            <h4>Nintendo64</h4>
            <p class="price">$99.99</p>
            <div class="product-actions">
                <button class="view-btn">View Product</button>
                <button class="cart-btn">Add to Cart</button>
            </div>
        </div>
    </div>
    <div class="see-more-btn">
        <button class="see-more">See more</button>
    </div>
</section>

      <jsp:include page="footer.jsp" />
</body>
</html>
