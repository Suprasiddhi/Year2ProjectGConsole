<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProjectGConsole Home Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/supportpage.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <section class="product-grid-section">
        <h1><b>Customer Support</b></h1>
        <div style="max-width: 800px; margin: auto; padding: 2rem; background: #fff; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,128,0,0.1);">
          <h2>How can we help you?</h2>
          <p class="description">Please fill out the form below or reach us through the contact information provided.</p>
          <form style="display: flex; flex-direction: column; gap: 1rem;">
            <input type="text" placeholder="Your Name" style="padding: 0.75rem; border-radius: 5px; border: 1px solid #ccc;" required>
            <input type="email" placeholder="Your Email" style="padding: 0.75rem; border-radius: 5px; border: 1px solid #ccc;" required>
            <textarea rows="5" placeholder="Describe your issue..." style="padding: 0.75rem; border-radius: 5px; border: 1px solid #ccc;" required></textarea>
            <button class="see-more" type="submit">Submit</button>
          </form>
        </div>
      </section>
      <jsp:include page="footer.jsp" />
</body>
</html>
