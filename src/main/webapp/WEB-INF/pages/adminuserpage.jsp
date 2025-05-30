<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>Admin - Customer Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminuserpage.css">
    <style>
        .success-message {
            color: green;
            margin-bottom: 10px;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="dashboard-container">
    <jsp:include page="sidebar.jsp" />

    <main class="main-content">
        <div class="dashboard-header">
            <h1 style="color: #1f2937;">Manage Customers</h1>
        </div>

        <c:if test="${not empty success}">
            <div class="success-message">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <div class="search-filter-wrapper">
            <label class="search-bar">Search Customer:
                <input type="text" id="customerSearch" placeholder="Search by username or name" onkeyup="searchByName()" />
            </label>

            <div class="filter-section">
                <label>Gender:
                    <select id="genderFilter">
                        <option value="">All</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </label>
                <label>Address:
                    <select id="addressFilter">
                        <option value="">All</option>
                        <option value="Has Address">Has Address</option>
                        <option value="No Address">No Address</option>
                    </select>
                </label>
                <button class="apply-filters-btn" onclick="applyFilters()">Apply Filters</button>
                <button class="reset-filters-btn" onclick="resetFilters()">Reset</button>
            </div>
        </div>

        <section class="table-section">
            <div style="display: flex; justify-content: space-between; align-items: center;">
                <h2>Customer List</h2>
 
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Gender</th>
                        <th>DOB</th>
                        <th>Address</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="customer" items="${customers}">
                        <tr>
                            <td>${customer.customer_username}</td>
                            <td>${customer.customer_name}</td>
                            <td>${customer.customer_email}</td>
                            <td>${customer.customer_phonenumber}</td>
                            <td>${customer.customer_gender}</td>
                            <td>${customer.customer_dob}</td>
                            <td>${customer.customer_address}</td>
                            <td>
                                
                                <form action="${pageContext.request.contextPath}/deletecustomer" method="POST" style="display:inline;">
                                    <input type="hidden" name="customerUsername" value="${customer.customer_username}">
                                    <button type="submit" class="delete-customer-btn" onclick="return confirm('Are you sure you want to delete ${customer.customer_name}?')">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>



<script>
function searchByName() {
    const searchTerm = document.getElementById('customerSearch').value.toLowerCase();
    const rows = document.querySelectorAll('tbody tr');
    rows.forEach(row => {
        const username = row.cells[0].textContent.toLowerCase();
        const name = row.cells[1].textContent.toLowerCase();
        row.style.display = (username.includes(searchTerm) || name.includes(searchTerm)) ? '' : 'none';
    });
}

function applyFilters() {
    const genderFilter = document.getElementById('genderFilter').value;
    const addressFilter = document.getElementById('addressFilter').value;
    const rows = document.querySelectorAll('tbody tr');
    rows.forEach(row => {
        const gender = row.cells[4].textContent.trim();
        const address = row.cells[6].textContent.trim();
        const matchGender = !genderFilter || gender === genderFilter;
        const matchAddress = !addressFilter || 
            (addressFilter === 'Has Address' && address) || 
            (addressFilter === 'No Address' && !address);
        row.style.display = (matchGender && matchAddress) ? '' : 'none';
    });
}

function resetFilters() {
    document.getElementById('customerSearch').value = '';
    document.getElementById('genderFilter').value = '';
    document.getElementById('addressFilter').value = '';
    document.querySelectorAll('tbody tr').forEach(row => {
        row.style.display = '';
    });
}

const modal = document.getElementById('modal-overlay');
const openBtn = document.getElementById('addCustomerBtn');
openBtn.addEventListener('click', () => {
    modal.style.display = 'flex';
    document.body.classList.add('modal-open');
});

function closeModal() {
    modal.style.display = 'none';
    document.body.classList.remove('modal-open');
}

const editModal = document.getElementById('edit-modal-overlay');
const editButtons = document.querySelectorAll('tbody .edit-customer-btn');
editButtons.forEach(button => {
    button.addEventListener('click', function () {
        const row = this.closest('tr');
        const username = row.cells[0].textContent;
        const name = row.cells[1].textContent;
        const email = row.cells[2].textContent;
        const phonenumber = row.cells[3].textContent;
        const gender = row.cells[4].textContent;
        const dob = row.cells[5].textContent;
        const address = row.cells[6].textContent;
        document.getElementById('editCustomerHiddenUsername').value = username;
        document.getElementById('editCustomerUsername').value = username;
        document.getElementById('editCustomerName').value = name;
        document.getElementById('editCustomerEmail').value = email;
        document.getElementById('editCustomerPhonenumber').value = phonenumber;
        document.getElementById('editCustomerGender').value = gender;
        document.getElementById('editCustomerDob').value = dob;
        document.getElementById('editCustomerAddress').value = address;
        editModal.style.display = 'flex';
        document.body.classList.add('modal-open');
    });
});

function closeEditModal() {
    editModal.style.display = 'none';
    document.body.classList.remove('modal-open');
}

document.getElementById('edit-customer-form').addEventListener('submit', function (e) {
    console.log('Submitting edit customer form');
});

document.addEventListener('DOMContentLoaded', function () {
    // Reset search and filter inputs on page load
    document.getElementById('customerSearch').value = '';
    document.getElementById('genderFilter').value = '';
    document.getElementById('addressFilter').value = '';
    // Ensure all rows are visible
    document.querySelectorAll('tbody tr').forEach(row => {
        row.style.display = '';
    });
});
</script>
</body>
</html>