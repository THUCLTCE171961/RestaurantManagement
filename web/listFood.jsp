<%-- 
    Document   : listFood
    Created on : Jun 5, 2024, 9:34:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DAO.FoodDAO" %>
<%@ page import="Model.FoodModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>List of Foods</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <h1>List of Foods</h1>

        <!-- Button to Add Food -->
        <form action="addFood.jsp" method="get">
            <input type="submit" value="Create New Food">
        </form>

        <form action="searchFood.jsp" method="get">
            <input type ="submit" value ="Search Food">
        </form>

        <hr>
        <!-- List of Foods -->
        <h2>Existing Foods</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Food ID</th>
                    <th>Food Image</th>
                    <th>Food Name</th>
                    <th>Category ID</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    FoodDAO foodDAO = new FoodDAO();
                    List<FoodModel> foodList = foodDAO.getAllFoods();
                    for (FoodModel food : foodList) {
                %>
                <tr>
                    <td><%= food.getFoodID() %></td>
                    <td><img src="image/<%= food.getFoodImg() %>" alt="<%= food.getFoodName() %>" width="50"></td>
                    <td><%= food.getFoodName() %></td>
                    <td><%= food.getCategoryID() %></td>
                    <td><%= food.getDescription() %></td>
                    <td><%= food.getPrice() %></td>
                    <td><%= food.getQuantity() %></td>
                    <td>
                        <!-- Button to Update Food -->
                        <form action="updateFood.jsp" method="get" style="display:inline;">
                            <input type="hidden" name="foodID" value="<%= food.getFoodID() %>">
                            <input type="submit" value="Update">
                        </form>
                        <!-- Button to Delete Food -->
                        <form action="deleteF" method="post" style="display:inline;">
                            <input type="hidden" name="foodID" value="<%= food.getFoodID() %>">
                            <td>
                                <button onclick="doDelete('${food.foodID}')">Delete</button>
                            </td>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <script>
            function doDelete(foodID) {
                if (confirm("Are you sure you want to delete food with foodID = " + foodID + "?")) {
                    window.location = "check?action=deleteP&foodID=" + foodID;
                }
            }
        </script>
        <div style="color: red;">${updateErr}</div>
        <div style="color: green;">${updateSuccess}</div>
        <div style="color: red;">${addErr}</div>
        <div style="color: green;">${addSuccess}</div>
    </body>
</html>
