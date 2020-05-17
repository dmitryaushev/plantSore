<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>${manufacturer.title}</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<table class="zui-table">
    <thead>
    <tr>
        <th>Title</th>
        <th>Products</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            ${manufacturer.title}
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty manufacturer.products}">
                    <c:forEach items="${manufacturer.products}" var="product">
                        <a href="${pageContext.request.contextPath}/plant/get?id=${product.id}">
                                ${product.title}</a><br>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No products yet</p>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    </tbody>
</table>
<security:authorize access="hasRole('ROLE_ADMIN')">
<a href="${pageContext.request.contextPath}/manufacturer/edit?id=${manufacturer.id}"
   class="button" role="button" tabindex="0">Edit</a><br>
</security:authorize>
</body>
</html>