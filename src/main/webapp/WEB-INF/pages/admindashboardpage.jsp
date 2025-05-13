<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admindashboardpage.css" />
</head>
<body>
  <jsp:include page="header.jsp" />
  <div class="dashboard-container">
    
<jsp:include page="sidebar.jsp" />
    <main class="main-content">
      <header class="dashboard-header">
        <h1>Admin Dashboard</h1>
      </header>

      <section class="cards-section">
        <div class="card">
          <h3>Total Users</h3>
          <p>1,024</p></div>
        <div class="card">
          <h3>Total Sales</h3>
          <p>$8,750</p></div>
        <div class="card">
          <h3>New Orders</h3>
          <p>150</p></div>
        <div class="card">
          <h3>Pending Tickets</h3>
          <p>12</p></div>
      </section>

      
      <!-- Table -->
      <section class="table-section">
        <h2>Recent Users</h2>
        <table>
          <thead>
            <tr>
              <th>User ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Registered Date</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <tr><td>U1001</td><td>Alice Smith</td><td>alice@example.com</td><td>2025-04-25</td><td>Active</td></tr>
            <tr><td>U1002</td><td>Bob Johnson</td><td>bob@example.com</td><td>2025-04-24</td><td>Inactive</td></tr>
            <tr><td>U1003</td><td>Charlie Rose</td><td>charlie@example.com</td><td>2024-12-02</td><td>Active</td></tr>
            <tr><td>U1004</td><td>Pratik Smith</td><td>bsmith@example.com</td><td>2024-12-02</td><td>Inactive</td></tr>
            <tr><td>U1005</td><td>Adita Rose</td><td>aditarose@example.com</td><td>2024-12-02</td><td>Active</td></tr>
          </tbody>
        </table>
      </section>
    </main>
  </div>
</body>
</html>
