<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Purchase Food Item</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>Purchase Food Item</h1>
<form action="${pageContext.request.contextPath}/consumer/purchase" method="post">
    <input type="hidden" name="id" value="${foodItem.id}">
    <p>Name: ${foodItem.name}</p>
    <p>Description: ${foodItem.description}</p>
    <p>Price: ${foodItem.price}</p>
    <p>Discounted Price: ${foodItem.discountedPrice}</p>
    <p>Max Quantity: ${foodItem.quantity}</p>
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" min="1" max="${foodItem.quantity}" required>
    <button type="submit">Purchase</button>
</form>
</body>
</html>
