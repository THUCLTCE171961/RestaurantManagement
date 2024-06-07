<%-- 
    Document   : updateFood
    Created on : Jun 5, 2024, 9:44:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.DrinkDAO" %>
<%@ page import="Model.DrinkModel" %>
<%
    String drinkID = request.getParameter("drinkID");
    DrinkDAO drinkDAO = new DrinkDAO();
    DrinkModel drink = drinkDAO.getDrinkById(drinkID);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Drink</title>
</head>
<body>
    <h1>Update Drink</h1>
    <form action="updateD" method="post" enctype="multipart/form-data">
        <input type="hidden" name="drinkID" value="<%= drinkID %>">
        <label for="drinkName">Drink Name:</label>
        <input type="text" id="drinkName" name="drinkName" value="<%= drink.getDrinkName() %>" required><br>

        <label for="beverageID">Beverage ID:</label>
        <input type="text" id="beverageID" name="beverageID" value="<%= drink.getBeverageID() %>" required><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required><%= drink.getDescription() %></textarea><br>

        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="<%= drink.getPrice() %>" required><br>

        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="<%= drink.getQuantity() %>" required><br>

        <label for="foodImg">Drink Image:</label>
        <input type="file" id="drinkImg" name="drinkImg"><br>

        <input type="submit" value="Update Drink">
    </form>
    <a href="listDrink.jsp">Back to Drink List</a>
</body>
</html>