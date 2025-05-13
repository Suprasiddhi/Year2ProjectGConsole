<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>Admin - Product Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminproductpage.css">
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
            <h1 style="color: #1f2937;">Manage Products</h1>
        </div>

        <c:if test="${not empty success}">
            <div class="success-message">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <div class="search-filter-wrapper">
            <label class="search-bar">Search Product:
                <input type="text" id="productSearch" placeholder="Search by name" onkeyup="searchByName()" />
            </label>

            <div class="filter-section">
                <label>Min Price: <input type="number" id="minPrice" placeholder="0" /></label>
                <label>Max Price: <input type="number" id="maxPrice" placeholder="1000" /></label>
                <label>Min Quantity: <input type="number" id="minQuantity" placeholder="0" /></label>
                <label>Max Quantity: <input type="number" id="maxQuantity" placeholder="100" /></label>
                <label>Stock:
                    <select id="stockFilter">
                        <option value="">All</option>
                        <option value="Yes">Yes</option>
                        <option value="No">No</option>
                    </select>
                </label>
                <button class="apply-filters-btn" onclick="applyFilters()">Apply Filters</button>
                <button class="reset-filters-btn" onclick="resetFilters()">Reset</button>
            </div>
        </div>

        <section class="table-section">
            <div style="display: flex; justify-content: space-between; align-items: center;">
                <h2>Product List</h2>
                <button id="addProductBtn" class="add-product-btn">Add Product</button>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product</th>
                        <th>Stock</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.product_id}</td>
                            <td>${product.product_name}</td>
                            <td>${product.product_stock == 1 ? 'Yes' : 'No'}</td>
                            <td>${product.product_quantity}</td>
                            <td>$${product.product_price}</td>
                            <td>
                                <button class="edit-product-btn">Edit</button>
                                <form action="${pageContext.request.contextPath}/deleteproduct" method="POST" style="display:inline;">
                                    <input type="hidden" name="productId" value="${product.product_id}">
                                    <button type="submit" class="delete-product-btn" onclick="return confirm('Are you sure you want to delete ${product.product_name}?')">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>

    <div id="modal-overlay" class="modal-overlay">
        <div class="modal-form">
            <h2>Add New Product</h2>
            <form id="add-product-form" action="${pageContext.request.contextPath}/addproduct" enctype="multipart/form-data" method="POST">
                <label for="productId">Product ID</label>
                <input type="text" id="productId" name="productId" required>
                <label for="productName">Product Name</label>
                <input type="text" id="productName" name="productName" required>
                <label for="stock">Stock</label>
                <select id="stock" name="stock">
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </select>
                <label for="quantity">Quantity</label>
                <input type="number" id="quantity" name="quantity" required>
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" required>
                <div class="modal-buttons">
                    <button type="submit" class="save-product-btn">Save</button>
                    <button type="button" class="cancel-btn" onclick="closeModal()">Cancel</button>
                </div>
            </form>
        </div>
    </div>

    <div id="edit-modal-overlay" class="modal-overlay">
        <div class="modal-form">
            <h2>Edit Product</h2>
            <form id="edit-product-form" action="${pageContext.request.contextPath}/editproduct" method="POST">
                <input type="hidden" id="editProductHiddenId" name="editProductHiddenId">
                <label for="editProductId">Product ID</label>
                <input type="text" id="editProductId" name="editProductId" required>
                <label for="editProductName">Product Name</label>
                <input type="text" id="editProductName" name="editProductName" required>
                <label for="editStock">Stock</label>
                <select id="editStock" name="editStock">
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </select>
                <label for="editQuantity">Quantity</label>
                <input type="number" id="editQuantity" name="editQuantity" required>
                <label for="editPrice">Price ($)</label>
                <input type="number" id="editPrice" name="editPrice" required>
                <div class="modal-buttons">
                    <button type="submit" class="update-product-btn">Update</button>
                    <button type="button" class="cancel-btn" onclick="closeEditModal()">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
function searchByName() {
    const searchTerm = document.getElementById('productSearch').value.toLowerCase();
    const rows = document.querySelectorAll('tbody tr');
    rows.forEach(row => {
        const productName = row.cells[1].textContent.toLowerCase();
        row.style.display = productName.includes(searchTerm) ? '' : 'none';
    });
}

function applyFilters() {
    const minPrice = parseFloat(document.getElementById('minPrice').value) || 0;
    const maxPrice = parseFloat(document.getElementById('maxPrice').value) || Infinity;
    const minQuantity = parseInt(document.getElementById('minQuantity').value) || 0;
    const maxQuantity = parseInt(document.getElementById('maxQuantity').value) || Infinity;
    const stockFilter = document.getElementById('stockFilter').value;
    const rows = document.querySelectorAll('tbody tr');
    rows.forEach(row => {
        const stock = row.cells[2].textContent.trim();
        const quantity = parseInt(row.cells[3].textContent);
        const price = parseFloat(row.cells[4].textContent.replace('$', ''));
        const matchStock = !stockFilter || stock === stockFilter;
        const matchQuantity = quantity >= minQuantity && quantity <= maxQuantity;
        const matchPrice = price >= minPrice && price <= maxPrice;
        row.style.display = (matchStock && matchQuantity && matchPrice) ? '' : 'none';
    });
}

function resetFilters() {
    document.getElementById('minPrice').value = '';
    document.getElementById('maxPrice').value = '';
    document.getElementById('minQuantity').value = '';
    document.getElementById('maxQuantity').value = '';
    document.getElementById('stockFilter').value = '';
    document.querySelectorAll('tbody tr').forEach(row => {
        row.style.display = '';
    });
}

const modal = document.getElementById('modal-overlay');
const openBtn = document.getElementById('addProductBtn');
openBtn.addEventListener('click', () => {
    modal.style.display = 'flex';
    document.body.classList.add('modal-open');
});

function closeModal() {
    modal.style.display = 'none';
    document.body.classList.remove('modal-open');
}

const editModal = document.getElementById('edit-modal-overlay');
const editButtons = document.querySelectorAll('tbody .edit-product-btn');
editButtons.forEach(button => {
    button.addEventListener('click', function () {
        const row = this.closest('tr');
        const id = row.cells[0].textContent;
        const name = row.cells[1].textContent;
        const stock = row.cells[2].textContent;
        const quantity = row.cells[3].textContent;
        const price = row.cells[4].textContent.replace('$', '');
        document.getElementById('editProductHiddenId').value = id;
        document.getElementById('editProductId').value = id;
        document.getElementById('editProductName').value = name;
        document.getElementById('editStock').value = stock;
        document.getElementById('editQuantity').value = quantity;
        document.getElementById('editPrice').value = price;
        editModal.style.display = 'flex';
        document.body.classList.add('modal-open');
    });
});

function closeEditModal() {
    editModal.style.display = 'none';
    document.body.classList.remove('modal-open');
}

document.getElementById('edit-product-form').addEventListener('submit', function (e) {
    console.log('Submitting edit product form');
});

document.addEventListener('DOMContentLoaded', function () {
    // Reset search and filter inputs on page load
    document.getElementById('productSearch').value = '';
    document.getElementById('minPrice').value = '';
    document.getElementById('maxPrice').value = '';
    document.getElementById('minQuantity').value = '';
    document.getElementById('maxQuantity').value = '';
    document.getElementById('stockFilter').value = '';
    // Ensure all rows are visible
    document.querySelectorAll('tbody tr').forEach(row => {
        row.style.display = '';
    });
});
</script>
</body>
</html>