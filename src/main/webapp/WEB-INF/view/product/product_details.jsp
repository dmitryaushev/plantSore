<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>${product.title}</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<table class="zui-table">
    <thead>
    <tr>
        <th>Title</th>
        <th>Price</th>
        <th>Manufacturer</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            ${product.title}
        </td>
        <td>
            ${product.price}$
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/manufacturer/get?id=${product.manufacturer.id}">
                ${product.manufacturer.title}</a><br>
        </td>
    </tr>
    </tbody>
</table>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <a href="${pageContext.request.contextPath}/plant/edit?id=${product.id}"
       class="button" role="button" tabindex="0">Edit</a><br>
</security:authorize>
</body>
</html>