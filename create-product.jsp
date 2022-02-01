<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Create product</title>
    </head>
    <body>
        <%--@elvariable id="product" type="ru.gb.entity.Product"--%>
        <form:form action="create" modelAttribute="product">
            <form:hidden path="id" value="${product.id}"/>
            TITLE: <form:input path="title"/>
            <br>
            PRICE: <form:input path="price"/>
            <br>
            <input type="submit" value="Save"/>
        </form:form>
    </body>
</html>