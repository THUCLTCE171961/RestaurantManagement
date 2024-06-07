<%-- 
    Document   : listFood
    Created on : Jun 5, 2024, 9:34:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DAO.DrinkDAO" %>
<%@ page import="Model.DrinkModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>List of Drink</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <h1>List of Drink</h1>

        <!-- Button to Add Food -->
        <form action="addDrink.jsp" method="get">
            <input type="submit" value="Create New Drink">
        </form>

        <form action="searchDrink.jsp" method="get">
            <input type ="submit" value ="Search Drink">
        </form>

        <hr>

        <h2>Existing Drink</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Drink ID</th>
                    <th>Drink Image</th>
                    <th>Drink Name</th>
                    <th>Beverage ID</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    DrinkDAO drinkDAO = new DrinkDAO();
                    List<DrinkModel> drinkList = drinkDAO.getAllDrinks();
                    for (DrinkModel drink : drinkList) {
                %>
                <tr>
                    <td><%= drink.getDrinkID() %></td>
                    <td><img src="image/<%= drink.getDrinkImg() %>" alt="<%= drink.getDrinkName() %>" width="50"></td>
                    <td><%= drink.getDrinkName() %></td>
                    <td><%= drink.getBeverageID() %></td>
                    <td><%= drink.getDescription() %></td>
                    <td><%= drink.getPrice() %></td>
                    <td><%= drink.getQuantity() %></td>
                    <td>
                        <!-- Button to Update Food -->
                        <form action="updateDrink.jsp" method="get" style="display:inline;">
                            <input type="hidden" name="drinkID" value="<%= drink.getDrinkID() %>">
                            <input type="submit" value="Update">
                        </form>
                        <!-- Button to Delete Food -->
                        <form action="deleteD" method="post" style="display:inline;">
                            <input type="hidden" name="drinkID" value="<%= drink.getDrinkID() %>">
                            <td>
                                <button onclick="doDelete('${drink.drinkID}')">Delete</button>
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
            function doDelete(drinkID) {
                if (confirm("Are you sure you want to delete drink with drinkID = " + drinkID + "?")) {
                    window.location = "check?action=deleteP&drinkID=" + drinkID;
                }
            }
        </script>
        <div style="color: red;">${updateErr}</div>
        <div style="color: green;">${updateSuccess}</div>
        <div style="color: red;">${addErr}</div>
        <div style="color: green;">${addSuccess}</div>
    </body>
</html>
