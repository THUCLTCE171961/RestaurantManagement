<%-- 
    Document   : addDrink
    Created on : Jun 6, 2024, 7:20:57 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Drink</title>
    </head>
    <body>
        <h2>Add Drink</h2>
        <form action="addD" method="post" enctype="multipart/form-data">
            <label for="drinkName">Drink Name:</label>
            <input type="text" id="drinkName" name="drinkName" required><br>

            <label for="beverageID">BeverageID ID:</label>
            <input type="text" id="beverageID" name="beverageID" required><br>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea><br>

            <label for="price">Price:</label>
            <input type="text" id="price" name="price" required><br>

            <label for="quantity">Quantity:</label>
            <input type="text" id="quantity" name="quantity" required><br>

            <label for="drinkImg">Drink Image:</label>
            <input type="file" id="drinkImg" name="drinkImg" required><br>

            <input type="submit" value="Add Drink">
        </form>
        <a href="listDrink.jsp">Back to drink List</a>
    </body>
</html>
