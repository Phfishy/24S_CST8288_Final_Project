<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Food Item List</title>
</head>
<body>
<h1>Food Item List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Retailer ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Discounted Price</th>
        <th>Expiration Date</th>
        <th>Surplus</th>
        <th>For Donation</th>
        <th>Created At</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="foodItem" items="${foodItems}">
        <tr>
            <td>${foodItem.id}</td>
            <td>${foodItem.retailerId}</td>
            <td>${foodItem.name}</td>
            <td>${foodItem.description}</td>
            <td>${foodItem.quantity}</td>
            <td>${foodItem.price}</td>
            <td>${foodItem.discountedPrice}</td>
            <td>${foodItem.expirationDate}</td>
            <td>${foodItem.isSurplus}</td>
            <td>${foodItem.isForDonation}</td>
            <td>${foodItem.createdAt}</td>
            <td>
                <a href="${pageContext.request.contextPath}/retailer/update?id=${foodItem.id}">Update</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
