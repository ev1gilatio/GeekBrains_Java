<%@ page import="ru.gb.springboot.model.Product" %>
<%@ page import="ru.gb.springboot.repository.ProductRepository" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>Product list</title>
    </head>
    <body>
        <h1>Product list:</h1>
        <ul>
            <% ProductRepository repository = new ProductRepository(); %>
            <% for (Product product : repository.getList()) { %>
            <h3>
                ID: <%=product.getId()%>
                <br>
                NAME: <%=product.getName()%>
                <br>
                PRICE: <%=product.getPrice()%>
                <br>
            </h3>
            <% } %>
        </ul>
    </body>
</html>