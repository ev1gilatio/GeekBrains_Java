<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Product list</title>
    </head>
    <body>
        <h1>Product list:</h1>
        <ul>
            <c:forEach var="product" items="${list}">
                <c:url var="viewUrl" value="/ + ${product.id}"/>
                <li>
                    <a href="${viewUrl}">VIEW</a>
                    <br>
                    ID: ${product.id}
                    <br>
                    NAME: ${product.name}
                    <br>
                    PRICE: ${product.price}
                </li>
                <br>
            </c:forEach>
        </ul>
        <c:url var="createUrl" value="/create">
            <c:param name="id" value="${product.id}"/>
        </c:url>

        <a href="${createUrl}">CREATE</a>
    </body>
</html>