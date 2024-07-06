<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Create Food Item</title>
</head>
<body>
<h1>Create Food Item</h1>
<form action="${pageContext.request.contextPath}/retailer/add" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required><br>
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required><br>
    <label for="price">Price:</label>
    <input type="text" id="price" name="price" required><br>
    <label for="discountedPrice">Discounted Price:</label>
    <input type="text" id="discountedPrice" name="discountedPrice" required><br>
    <label for="expirationDate">Expiration Date:</label>
    <input type="date" id="expirationDate" name="expirationDate" required><br>
    <label for="isSurplus">Is Surplus:</label>
    <input type="checkbox" id="isSurplus" name="isSurplus"><br>
    <label for="isForDonation">Is For Donation:</label>
    <input type="checkbox" id="isForDonation" name="isForDonation"><br>
    <input type="submit" value="Add Food Item">

</form>
</body>
</html>
