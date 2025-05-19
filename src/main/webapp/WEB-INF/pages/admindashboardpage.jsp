<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admindashboardpage.css" />
  <!-- Include Chart.js CDN -->
  <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.3/dist/chart.umd.min.js"></script>
  <style>
    .chart-section {
      margin-top: 20px;
      padding: 20px;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    .chart-container-wrapper {
      display: flex;
      justify-content: center;
      gap: 20px;
      flex-wrap: wrap;
    }
    .chart-container {
      width: 350px;
      max-width: 100%;
      text-align: center;
    }
    .error-message {
      color: red;
      margin-bottom: 10px;
      text-align: center;
    }
  </style>
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
          <p><%= request.getAttribute("totalUsers") %></p>
        </div>
        <div class="card">
          <h3>Total Products</h3>
          <p><%= request.getAttribute("totalProducts") %></p>
        </div>
        <div class="card">
          <h3>Total Orders</h3>
          <p><%= request.getAttribute("totalOrders") %></p>
        </div>
        <div class="card">
          <h3>Pending Orders</h3>
          <p><%= request.getAttribute("totalPendingOrders") %></p>
        </div>
      </section>

      <!-- Pie Charts -->
      <section class="chart-section">

        <div class="chart-container-wrapper">
          <!-- Order Status Chart -->
          <div class="chart-container">
            <h3>Order Status</h3>
            <% if (request.getAttribute("pendingOrdersError") != null) { %>
              <div class="error-message"><%= request.getAttribute("pendingOrdersError") %></div>
            <% } %>
            <% if (request.getAttribute("deliveredOrdersError") != null) { %>
              <div class="error-message"><%= request.getAttribute("deliveredOrdersError") %></div>
            <% } %>
            <canvas id="orderStatusChart"></canvas>
          </div>
          <!-- Gender Distribution Chart -->
          <div class="chart-container">
            <h3>Customer Gender</h3>
            <% if (request.getAttribute("maleCustomersError") != null) { %>
              <div class="error-message"><%= request.getAttribute("maleCustomersError") %></div>
            <% } %>
            <% if (request.getAttribute("femaleCustomersError") != null) { %>
              <div class="error-message"><%= request.getAttribute("femaleCustomersError") %></div>
            <% } %>
            <canvas id="genderDistributionChart"></canvas>
          </div>
        </div>
      </section>
    </main>
  </div>

  <script>
    document.addEventListener('DOMContentLoaded', function () {
      // Order Status Chart
      const ctxOrder = document.getElementById('orderStatusChart').getContext('2d');
      const pendingOrders = <%= request.getAttribute("totalPendingOrders") != null && (int)request.getAttribute("totalPendingOrders") >= 0 ? request.getAttribute("totalPendingOrders") : 0 %>;
      const deliveredOrders = <%= request.getAttribute("totalDeliveredOrders") != null && (int)request.getAttribute("totalDeliveredOrders") >= 0 ? request.getAttribute("totalDeliveredOrders") : 0 %>;
      new Chart(ctxOrder, {
        type: 'pie',
        data: {
          labels: ['Pending', 'Delivered'],
          datasets: [{
            label: 'Order Status Distribution',
            data: [pendingOrders, deliveredOrders],
            backgroundColor: [
              'rgba(150, 40, 27)',  // Red for Pending
              'rgba(201, 242, 155)'   // Green for delivered
            ],
            borderColor: [
              'rgba(150, 40, 27)',
              'rgba(201, 242, 155)'
            ],
            borderWidth: 1
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: { position: 'top' },
            title: { display: false }  // Title moved to h3
          }
        }
      });

      // Gender Distribution Chart
      const ctxGender = document.getElementById('genderDistributionChart').getContext('2d');
      const maleCustomers = <%= request.getAttribute("totalMaleCustomers") != null && (int)request.getAttribute("totalMaleCustomers") >= 0 ? request.getAttribute("totalMaleCustomers") : 0 %>;
      const femaleCustomers = <%= request.getAttribute("totalFemaleCustomers") != null && (int)request.getAttribute("totalFemaleCustomers") >= 0 ? request.getAttribute("totalFemaleCustomers") : 0 %>;
      new Chart(ctxGender, {
        type: 'pie',
        data: {
          labels: ['Male', 'Female'],
          datasets: [{
            label: 'Customer Gender Distribution',
            data: [maleCustomers, femaleCustomers],
            backgroundColor: [
              'rgba(54, 162, 235, 0.7)',  // Blue for Male
              'rgba(255, 105, 180, 0.7)'  // Pink for Female
            ],
            borderColor: [
              'rgba(54, 162, 235, 1)',
              'rgba(255, 105, 180, 1)'
            ],
            borderWidth: 1
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: { position: 'top' },
            title: { display: false }  // Title moved to h3
          }
        }
      });
    });
  </script>
</body>
</html>