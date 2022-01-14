<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page contentType="text/html; character=utf-8" language="java" %>

<html>
    <head>
        <title>
            Products list
        </title>
    </head>
    <body>
        <h1>Products</h1>
            <%for (Product prod : (List<Product>) (request.getAttribute("product"))) { %>
            <hr>
            <ul>
                <li>PRODUCT <%=prod.getId() %></li>
                <li>TITLE: <%=prod.getTitle() %></li>
                <li>COST: <%=prod.getCost() %></li>
            </ul>
            <%} %>
            <hr>
    </body>
</html>