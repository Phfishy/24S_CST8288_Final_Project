<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>
<h1>User Registration</h1>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="type">User Type:</label>
    <select id="type" name="type" required>
        <option value="1">Consumer</option>
        <option value="5">Retailer</option>
        <option value="10">Charitable Organization</option>
    </select><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="submit" value="Register">
</form>
</body>
</html>