<%-- 
    Document   : searchFood
    Created on : Jun 6, 2024, 6:20:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search Food</h1>

        <div class="search-container">
            <form action="searchF" method="get">
                <input type="text" name="txtSearch" placeholder="Enter food name" required>
                <button type="submit">Search</button>
            </form>
        </div>

        <c:if test="${not empty searchError}">
            <div style="color: red;">${searchError}</div>
        </c:if>

        <c:if test="${not empty data}">
            <table>
                <thead>
                    <tr>
                        <th>Food ID</th>
                        <th>Food Image</th>
                        <th>Food Name</th>
                        <th>Category ID</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="food" items="${data}">
                        <tr>
                            <td>${food.foodID}</td>
                            <td><img src="image/${food.foodImg}" alt="${food.foodName}" width="50"></td>
                            <td>${food.foodName}</td>
                            <td>${food.categoryID}</td>
                            <td>${food.description}</td>
                            <td>${food.price}</td>
                            <td>${food.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
