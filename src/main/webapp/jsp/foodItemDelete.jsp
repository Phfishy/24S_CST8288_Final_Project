<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Delete Food Item</title>
</head>
<body>
<h1>Delete Food Item</h1>
<form action="${pageContext.request.contextPath}/retailer/delete" method="post">
    <input type="hidden" id="id" name="id" value="${foodItem.id}">
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" value="${foodItem.quantity}" readonly><br>
    <label for="expirationDate">Expiration Date:</label>
    <input type="date" id="expirationDate" name="expirationDate" value="${foodItem.expirationDate}" readonly><br>
    <label for="isSurplus">Is Surplus:</label>
    <input type="checkbox" id="isSurplus" name="isSurplus" ${foodItem.isSurplus ? 'checked' : ''} readonly><br>
    <input type="submit" value="Delete Food Item">
</form>
</body>
</html>
