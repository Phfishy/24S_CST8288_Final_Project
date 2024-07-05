<!DOCTYPE html>
<html>
<head>
    <title>Update Food Item</title>
</head>
<body>
<h1>Update Food Item</h1>
<form action="${pageContext.request.contextPath}/retailer/update" method="post">
    <input type="hidden" id="id" name="id" value="${foodItem.id}">
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" value="${foodItem.quantity}" required><br>
    <label for="expirationDate">Expiration Date:</label>
    <input type="date" id="expirationDate" name="expirationDate" value="${foodItem.expirationDate}" required><br>
    <label for="isSurplus">Is Surplus:</label>
    <input type="checkbox" id="isSurplus" name="isSurplus" ${foodItem.isSurplus ? 'checked' : ''}><br>
    <input type="submit" value="Update Food Item">
</form>
</body>
</html>
