<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Product</title>
    </head>
    <body>
        <h2>ID: ${product.id}</h2>
        <h2>TITLE: ${product.title}</h2>
        <h2>PRICE: ${product.price}</h2>

        <c:url var="editUrl" value="/product/edit">
            <c:param name="id" value="${product.id}"/>
        </c:url>

        <a href="${editUrl}">EDIT</a>
    </body>
</html>