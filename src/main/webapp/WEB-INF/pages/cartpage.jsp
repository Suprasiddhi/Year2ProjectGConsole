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

      <jsp:include page="footer.jsp" />
</body>
</html>




