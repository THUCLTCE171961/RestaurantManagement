<%-- 
    Document   : updateFood
    Created on : Jun 5, 2024, 9:44:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.FoodDAO" %>
<%@ page import="Model.FoodModel" %>
<%
    String foodID = request.getParameter("foodID");
    FoodDAO foodDAO = new FoodDAO();
    FoodModel food = foodDAO.getFoodById(foodID);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Food</title>
</head>
<body>
    <h1>Update Food</h1>
    <form action="updateF" method="post" enctype="multipart/form-data">
        <input type="hidden" name="foodID" value="<%= foodID %>">
        <label for="foodName">Food Name:</label>
        <input type="text" id="foodName" name="foodName" value="<%= food.getFoodName() %>" required><br>

        <label for="categoryID">Category ID:</label>
        <input type="text" id="categoryID" name="categoryID" value="<%= food.getCategoryID() %>" required><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required><%= food.getDescription() %></textarea><br>

        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="<%= food.getPrice() %>" required><br>

        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="<%= food.getQuantity() %>" required><br>

        <label for="foodImg">Food Image:</label>
        <input type="file" id="foodImg" name="foodImg"><br>

        <input type="submit" value="Update Food">
    </form>
    <a href="listFoods.jsp">Back to Food List</a>
</body>
</html>