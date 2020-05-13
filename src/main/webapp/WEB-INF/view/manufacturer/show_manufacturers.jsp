<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Manufacturers</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<c:if test="${not empty manufacturers}">
    <table class="zui-table">
        <thead>
        <tr>
            <th>Title</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${manufacturers}" var="manufacturer">
            <tr>
                <td>
                        ${manufacturer.title}
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/manufacturer/get?id=${manufacturer.id}"
                       class="button" role="button" tabindex="0">Detailed</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>