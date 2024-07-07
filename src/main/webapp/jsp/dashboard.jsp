<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Food Waste Reduction Platform</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <script type="text/javascript">
        function postForm(url) {
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", url);

            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>
<h1>Dashboard</h1>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Discounted Price</th>
        <th>Expiration Date</th>
        <th>Is Surplus</th>
        <th>Is For Donation</th>
        <th>Created At</th>
        <c:if test="${userType == 5}">
            <th>Actions</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="foodItem" items="${foodItems}">
        <tr>
            <td>${foodItem.id}</td>
            <td>${foodItem.name}</td>
            <td>${foodItem.description}</td>
            <td>${foodItem.quantity}</td>
            <td>${foodItem.price}</td>
            <td>${foodItem.discountedPrice}</td>
            <td>${foodItem.expirationDate}</td>
            <td>${foodItem.isSurplus}</td>
            <td>${foodItem.isForDonation}</td>
            <td>${foodItem.createdAt}</td>
            <c:choose>
                <c:when test="${userType == 5}">
                    <td>
                        <a href="${pageContext.request.contextPath}/retailer/update?id=${foodItem.id}">Edit</a>
                        <a href="#"
                           onclick="postForm('${pageContext.request.contextPath}/retailer/delete?id=${foodItem.id}')">Delete</a>
                        <c:if test="${!foodItem.isSurplus}">
                            <a href="#"
                               onclick="postForm('${pageContext.request.contextPath}/retailer/markAsSurplus?id=${foodItem.id}')">Mark
                                as Surplus</a>
                        </c:if>
                    </td>
                </c:when>
                <c:when test="${userType == 10}">
                    <td>
                        <a href="${pageContext.request.contextPath}/organization/claim?id=${foodItem.id}">Claim for
                            Donation</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <a href="${pageContext.request.contextPath}/consumer/purchase?id=${foodItem.id}">Purchase</a>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${userType == 5}">
    <a href="${pageContext.request.contextPath}/retailer/add">Add New Food Item</a>
</c:if>

<c:if test="${userType == 1 || userType == 10}">
    <c:choose>
        <c:when test="${hasSubscription}">
            <p>You are subscribed to surplus food alerts.</p>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/subscription">Subscribe to Surplus Food Alerts</a>
        </c:otherwise>
    </c:choose>
</c:if>

</body>
</html>