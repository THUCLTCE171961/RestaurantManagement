<%-- 
    Document   : addFood
    Created on : Jun 5, 2024, 8:28:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Food</title>
    </head>
    <body>
        <h2>Add Food</h2>
        <form action="addF" method="post" enctype="multipart/form-data">
            <label for="foodName">Food Name:</label>
            <input type="text" id="foodName" name="foodName" required><br>

            <label for="categoryID">Category ID:</label>
            <input type="text" id="categoryID" name="categoryID" required><br>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea><br>

            <label for="price">Price:</label>
            <input type="text" id="price" name="price" required><br>

            <label for="quantity">Quantity:</label>
            <input type="text" id="quantity" name="quantity" required><br>

            <label for="foodImg">Food Image:</label>
            <input type="file" id="foodImg" name="foodImg" required><br>

            <input type="submit" value="Add Food">
        </form>
        <a href="listFood.jsp">Back to Food List</a>
    </body>
</html>
