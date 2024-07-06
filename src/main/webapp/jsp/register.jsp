<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Food Waste Reduction Platform</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="email"], input[type="password"], select {
            width: 200px;
            padding: 5px;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Register for Food Waste Reduction Platform</h1>

<% if (request.getAttribute("error") != null) { %>
<p class="error"><%= request.getAttribute("error") %>
</p>
<% } %>

<form action="${pageContext.request.contextPath}/user/register" method="post">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <div class="form-group">
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>

    <div class="form-group">
        <label for="type">User Type:</label>
        <select id="type" name="type" required>
            <option value="1">Consumer</option>
            <option value="5">Retailer</option>
            <option value="10">Charitable Organization</option>
        </select>
    </div>

    <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" id="address" name="address">
    </div>

    <div class="form-group">
        <label for="contractNumber">Contract Number (for Retailers):</label>
        <input type="text" id="contractNumber" name="contractNumber">
    </div>

    <div class="form-group">
        <input type="submit" value="Register">
    </div>
</form>

<p>Already have an account? <a href="${pageContext.request.contextPath}/login.jsp">Login here</a></p>
</body>
</html>