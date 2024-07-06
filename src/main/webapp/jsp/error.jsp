<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - Food Waste Reduction Platform</title>
</head>
<body>
<h1>An Error Occurred</h1>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %>
</p>
<% } else { %>
<p>An unexpected error occurred. Please try again later.</p>
<% } %>
<a href="${pageContext.request.contextPath}/">Return to Home</a>
</body>
</html>