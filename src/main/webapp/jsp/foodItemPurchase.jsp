<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Purchase Food Item</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        .food-item-details {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 15px;
            margin-bottom: 20px;
        }
        .food-item-details p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<h1>Purchase Food Item</h1>
<form action="${pageContext.request.contextPath}/consumer/purchase" method="post">
    <input type="hidden" name="id" value="${foodItem.id}">
    <div class="food-item-details">
        <p><strong>Name:</strong> ${foodItem.name}</p>
        <p><strong>Description:</strong> ${foodItem.description}</p>
        <p><strong>Price:</strong> $${foodItem.price}</p>
        <p><strong>Discounted Price:</strong> $${foodItem.discountedPrice}</p>
        <p><strong>Max Quantity:</strong> ${foodItem.quantity}</p>
    </div>
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" min="1" max="${foodItem.quantity}" required>
    <input type="submit" value="Purchase">
</form>
</body>
</html>