<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', sans-serif;
        }

        body {
            background: linear-gradient(to right, #ccc, #6db800);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background: white;
            width: 90%;
            max-width: 1000px;
            display: flex;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        }

        .info-section {
            color: white;
            padding: 40px;
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            text-align: center;
            background: linear-gradient(to bottom, #ccc, #6db800);
        }

        .info-section h2 {
            margin-bottom: 15px;
        }

        .form-section {
            flex: 2;
            padding: 40px;
            background: white;
        }

        .form-section h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        form input,
        form select {
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            transition: border-color 0.3s;
        }

        form input:focus,
        form select:focus {
            border-color: #6db800;
            outline: none;
        }

        .save-btn {
            padding: 12px;
            background-color: #6db800;
            border: none;
            border-radius: 25px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .save-btn:hover {
            background-color: #528a00;
        }

        .profile-image-wrapper {
            position: relative;
            width: 100px;
            height: 100px;
            margin: 0 auto 20px;
        }

        .profile-image-label {
            display: block;
            width: 100%;
            height: 100%;
            cursor: pointer;
            position: relative;
        }

        .profile-image {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 50%;
            border: 3px solid #6db800;
        }

        .message {
            text-align: center;
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 8px;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="info-section">
        <h2>Edit Profile</h2>
    </div>
    <div class="form-section">
        <h2>Update Your Information</h2>
        <c:if test="${not empty message}">
            <div class="message error">${message}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/editprofile" method="post" enctype="multipart/form-data">
            <div class="profile-image-wrapper">
                <label for="profileImage" class="profile-image-label">
                    <img src="${pageContext.request.contextPath}/resources/images/customer/${empty user.customer_image ? 'ppdefault.png' : user.customer_image}?t=${System.currentTimeMillis()}" alt="Profile Image" class="profile-image" />
                </label>
                <input type="file" id="profileImage" name="profileImage" accept="image/*" style="display: none;" onchange="previewImage(event)" />
            </div>
            <input type="text" placeholder="Name" name="name" required value="${user.customer_name}" />
            <input type="email" placeholder="Email" name="email" required value="${user.customer_email}" />
            <input type="tel" placeholder="Phone Number" name="phonenumber" required value="${user.customer_phonenumber}" />
            <input type="text" placeholder="Address" name="address" required value="${user.customer_address}" />
            <select name="gender" required>
                <option value="">Select Gender</option>
                <option value="Male" ${user.customer_gender == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${user.customer_gender == 'Female' ? 'selected' : ''}>Female</option>
                <option value="Other" ${user.customer_gender == 'Other' ? 'selected' : ''}>Other</option>
            </select>
            <input type="date" placeholder="Birthday" name="dob" required value="${user.customer_dob}" />
            <button type="submit" class="save-btn">Save</button>
        </form>
    </div>
</div>
<script>
    function previewImage(event) {
        const reader = new FileReader();
        reader.onload = function() {
            const output = document.querySelector('.profile-image');
            output.src = reader.result;
            console.log('Image preview updated');
        };
        reader.readAsDataURL(event.target.files[0]);
    }
</script>
</body>
</html>