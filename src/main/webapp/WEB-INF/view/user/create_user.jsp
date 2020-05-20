<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Create User</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<table class="zui-table">
    <form:form id="form" name="form" method="post" action="create" modelAttribute="user">
    <h1>Create user form</h1>
    <tr>
        <td>
            <p>First name</p>
        </td>
        <td>
            <form:input type="text" path="firstName" id="firstName"/>
            <form:errors path="firstName" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <td>
            <p>Last name</p>
        </td>
        <td>
            <form:input type="text" path="lastName" id="lastName"/>
            <form:errors path="lastName" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <td>
            <p>Email</p>
        </td>
        <td>
            <form:input type="text" path="email" id="email"/>
            <form:errors path="email" cssClass="error"/>
                ${message}
        </td>
    </tr>
    <tr>
        <td>
            <p>Password</p>
        </td>
        <td>
            <form:input type="password" path="password" id="password"/>
            <form:errors path="password" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <td>
            <p>Role</p>
        </td>
        <td>
            <form:select path="role">
                <c:forEach items="${roles}" var="role">
                    <form:option value="${role.id}">
                        <c:set var="str" value="${fn:replace(role.title, 'ROLE_', '')}"/>
                        ${fn:substring(str, 0, 1)}${fn:toLowerCase(fn:substring(str, 1, fn:length(str)))}
                    </form:option>
                </c:forEach>
            </form:select>
        </td>
    </tr>
</table>
<button type="submit" class="button">Create</button>
<div class="spacer"></div>
</form:form>
</body>
</html>