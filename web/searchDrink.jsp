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
        <h1>Search Drink</h1>

        <div class="search-container">
            <form action="searchD" method="get">
                <input type="text" name="txtSearch" placeholder="Enter drink name" required>
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
                        <th>Drink ID</th>
                        <th>Drink Image</th>
                        <th>Drink Name</th>
                        <th>Beverage ID</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="drink" items="${data}">
                        <tr>
                            <td>${drink.drinkID}</td>
                            <td><img src="image/${drink.drinkImg}" alt="${drink.drinkName}" width="50"></td>
                            <td>${drink.drinkName}</td>
                            <td>${drink.beverageID}</td>
                            <td>${drink.description}</td>
                            <td>${drink.price}</td>
                            <td>${drink.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
