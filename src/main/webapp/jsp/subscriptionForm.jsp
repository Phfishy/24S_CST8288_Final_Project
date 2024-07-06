<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Subscribe to Surplus Food Alerts</title>
</head>
<body>
<h1>Subscribe to Surplus Food Alerts</h1>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/subscription" method="post">
    <label for="communicationMethod">Communication Method:</label>
    <select name="communicationMethod" id="communicationMethod">
        <option value="1">Email</option>
        <option value="2">Phone</option>
    </select><br><br>

    <label for="foodPreference">Food Preference:</label>
    <input type="text" name="foodPreference" id="foodPreference"><br><br>

    <label for="latitude">Latitude:</label>
    <input type="text" name="latitude" id="latitude"><br><br>

    <label for="longitude">Longitude:</label>
    <input type="text" name="longitude" id="longitude"><br><br>

    <input type="submit" value="Subscribe">
</form>
</body>
</html>