<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>Manage Orders</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminorderpage.css" />
  <style>
    .table-section {
      margin-top: 2rem;
      padding: 1.5rem;
      background: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.05);
    }
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      padding: 0.75rem;
      text-align: left;
      border-bottom: 1px solid #e5e7eb;
    }
    th {
      background-color: #f9fafb;
      color: #374151;
    }
    td {
      color: #4b5563;
    }
    .success-message {
      color: green;
      margin-bottom: 10px;
      text-align: center;
    }
    .error-message {
      color: red;
      margin-bottom: 10px;
      text-align: center;
    }
    .modal-overlay {
      display: none;
      position: fixed;
      top: 0;
      left: 0;
      width: 100vw;
      height: 100vh;
      backdrop-filter: blur(5px);
      background-color: rgba(0, 0, 0, 0.2);
      justify-content: center;
      align-items: center;
      z-index: 9999;
    }
    .modal-form {
      background-color: #fff;
      padding: 2rem;
      border-radius: 10px;
      box-shadow: 0 5px 15px rgba(0,0,0,0.2);
      width: 400px;
      max-width: 90%;
    }
    .modal-form h2 {
      margin-bottom: 1rem;
      color: #1f2937;
    }
    .modal-form label {
      display: block;
      margin-bottom: 5px;
      color: #4b5563;
    }
    .modal-form input, .modal-form select {
      width: 100%;
      padding: 0.6rem;
      margin-bottom: 1rem;
      border-radius: 5px;
      border: 1px solid #e5e7eb;
      font-size: 1rem;
    }
    .modal-buttons {
      display: flex;
      justify-content: space-between;
    }
    .modal-buttons button {
      padding: 0.6rem 1.2rem;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .save-order-btn {
      background-color: #388e3c;
      color: white;
    }
    .save-order-btn:hover {
      background-color: #81c784;
    }
    .cancel-btn {
      background-color: #e5e7eb;
      color: #4b5563;
    }
    .cancel-btn:hover {
      background-color: #d1d5db;
    }
    .add-order-btn {
      padding: 0.5rem 1rem;
      background-color: #81c784;
      color: white;
      border: none;
      border-radius: 25px;
      cursor: pointer;
      transition: background-color 0.3s ease;
      font-weight: bold;
    }
    .add-order-btn:hover {
      background-color: #388e3c;
    }
    .modal-open {
      overflow: hidden;
    }
    .search-filter-wrapper {
      margin-bottom: 1.5rem;
    }
    .search-bar {
      display: block;
      margin-bottom: 1rem;
    }
    .search-bar input {
      width: 100%;
      max-width: 300px;
      padding: 0.6rem;
      font-size: 0.9rem;
      border-radius: 25px;
      border: 1px solid #ddd;
      outline: none;
      transition: border-color 0.3s ease;
      margin-top: 0.5rem;
    }
    .search-bar input::placeholder {
      color: #aaa;
    }
    .search-bar input:focus {
      border-color: #388e3c;
    }
  </style>
</head>
<body>
  <jsp:include page="header.jsp" />
  <div class="dashboard-container">
    <jsp:include page="sidebar.jsp" />
    <main class="main-content">
      <div class="dashboard-header">
        <h1 style="color: #1f2937;">Manage Orders</h1>
      </div>

      <c:if test="${not empty success}">
        <div class="success-message">${success}</div>
      </c:if>
      <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
      </c:if>

      <div class="search-filter-wrapper">
        <label class="search-bar">Search by Customer Username:
          <input type="text" id="orderSearch" placeholder="Search by customer username" onkeyup="searchByUsername()" />
        </label>
      </div>

      <section class="table-section">
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <h2>Order List</h2>
          <button id="addOrderBtn" class="add-order-btn">Add Order</button>
        </div>
        <table>
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Customer Username</th>
              <th>Quantity</th>
              <th>Price</th>
              <th>Delivery Type</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="order" items="${orders}">
              <tr>
                <td>${order.order_id}</td>
                <td>${order.customer_username}</td>
                <td>${order.order_quantity}</td>
                <td>${order.order_price}</td>
                <td>${order.order_deliverytype}</td>
                <td>
                  <c:choose>
                    <c:when test="${order.order_status == 0}">Pending</c:when>
                    <c:when test="${order.order_status == 1}">Delivered</c:when>
                    <c:otherwise>Cancelled</c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </section>
    </main>

    <div id="modal-overlay" class="modal-overlay">
      <div class="modal-form">
        <h2>Add New Order</h2>
        <form id="add-order-form" action="${pageContext.request.contextPath}/addorder" method="POST">
          <label for="orderId">Order ID</label>
          <input type="text" id="orderId" name="orderId" required>
          <label for="customerUsername">Customer Username</label>
          <select id="customerUsername" name="customerUsername" required>
            <option value="">Select Customer</option>
            <c:forEach var="username" items="${customerUsernames}">
              <option value="${username}">${username}</option>
            </c:forEach>
          </select>
          <label for="quantity">Quantity</label>
          <input type="number" id="quantity" name="quantity" required>
          <label for="price">Price</label>
          <input type="number" id="price" name="price" step="0.01" required>
          <label for="deliveryType">Delivery Type</label>
          <select id="deliveryType" name="deliveryType" required>
            <option value="Standard">Standard</option>
            <option value="Express">Express</option>
          </select>
          <label for="status">Status</label>
          <select id="status" name="status" required>

            <option value="Pending">Pending</option>
            <option value="Delivered">Delivered</option>

          </select>
          <div class="modal-buttons">
            <button type="submit" class="save-order-btn">Save</button>
            <button type="button" class="cancel-btn" onclick="closeModal()">Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script>
    function searchByUsername() {
      const searchTerm = document.getElementById('orderSearch').value.toLowerCase();
      const rows = document.querySelectorAll('tbody tr');
      rows.forEach(row => {
        const customerUsername = row.cells[1].textContent.toLowerCase();
        row.style.display = customerUsername.includes(searchTerm) ? '' : 'none';
      });
    }

    const modal = document.getElementById('modal-overlay');
    const openBtn = document.getElementById('addOrderBtn');

    openBtn.addEventListener('click', () => {
      modal.style.display = 'flex';
      document.body.classList.add('modal-open');
    });

    function closeModal() {
      modal.style.display = 'none';
      document.body.classList.remove('modal-open');
    }

    document.getElementById('add-order-form').addEventListener('submit', function (e) {
      console.log('Submitting add order form');
    });

    document.addEventListener('DOMContentLoaded', function () {
      modal.style.display = 'none';
      document.getElementById('orderSearch').value = '';
      document.querySelectorAll('tbody tr').forEach(row => {
        row.style.display = '';
      });
    });
  </script>
</body>
</html>