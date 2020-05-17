<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>${user.firstName} ${user.lastName}</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<table class="zui-table">
    <thead>
    <tr>
        <th>Full name</th>
        <th>Email</th>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <th>Role</th>
        </security:authorize>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            ${user.firstName} ${user.lastName}
        </td>
        <td>
            ${user.email}
        </td>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <td>
                    ${user.role.role}
            </td>
        </security:authorize>
    </tr>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/user/edit?id=${user.id}"
   class="button" role="button" tabindex="0">Edit</a><br>
</body>
</html>