<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Food Waste Reduction Platform</title>
</head>
<body>
<h1>Dashboard</h1>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Discounted Price</th>
        <th>Expiration Date</th>
        <c:if test="${userType == 5}">
            <th>Actions</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="foodItem" items="${foodItems}">
        <tr>
            <td>${foodItem.name}</td>
            <td>${foodItem.description}</td>
            <td>${foodItem.quantity}</td>
            <td>${foodItem.price}</td>
            <td>${foodItem.discountedPrice}</td>
            <td>${foodItem.expirationDate}</td>
            <c:choose>
                <c:when test="${userType == 5}">
                    <td>
                        <a href="${pageContext.request.contextPath}/foodItem/edit?id=${foodItem.id}">Edit</a>
                        <a href="${pageContext.request.contextPath}/foodItem/delete?id=${foodItem.id}">Delete</a>
                        <c:if test="${!foodItem.isSurplus}">
                            <a href="${pageContext.request.contextPath}/foodItem/markAsSurplus?id=${foodItem.id}">Mark as Surplus</a>
                        </c:if>
                    </td>
                </c:when>
                <c:when test="${userType == 10}">
                    <td>
                        <a href="${pageContext.request.contextPath}/foodItem/claim?id=${foodItem.id}">Claim for Donation</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <a href="${pageContext.request.contextPath}/foodItem/purchase?id=${foodItem.id}">Purchase</a>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${userType == 5}">
    <a href="${pageContext.request.contextPath}/foodItem/add">Add New Food Item</a>
</c:if>
</body>
</html>