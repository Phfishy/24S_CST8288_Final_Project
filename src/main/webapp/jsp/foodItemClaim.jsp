<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Claim Food Item</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>Claim Food Item</h1>
<form action="${pageContext.request.contextPath}/organization/claim" method="post">
    <input type="hidden" name="id" value="${foodItem.id}">
    <div class="food-item-details">
        <p><strong>Name:</strong> ${foodItem.name}</p>
        <p><strong>Description:</strong> ${foodItem.description}</p>
        <p><strong>Price:</strong> $${foodItem.price}</p>
        <p><strong>Max Quantity:</strong> ${foodItem.quantity}</p>
    </div>
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" min="1" max="${foodItem.quantity}" required>
    <input type="submit" value="Claim">
</form>
</body>
</html>