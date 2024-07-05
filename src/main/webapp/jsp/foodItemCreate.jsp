<!DOCTYPE html>
<html>
<head>
    <title>Create Food Item</title>
</head>
<body>
<h1>Create Food Item</h1>
<form action="${pageContext.request.contextPath}/retailer/add" method="post">
    <label for="retailerId">Retailer ID:</label>
    <input type="text" id="retailerId" name="retailerId" required><br>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required><br>
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required><br>
    <label for="price">Price:</label>
    <input type="text" id="price" name="price" required><br>
    <label for="discountedPrice">Discounted Price:</label>
    <input type="text" id="discountedPrice" name="discountedPrice" required><br>
    <label for="expirationDate">Expiration Date:</label>
    <input type="date" id="expirationDate" name="expirationDate" required><br>
    <input type="submit" value="Add Food Item">
</form>
</body>
</html>
