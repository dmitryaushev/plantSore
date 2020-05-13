<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Create Manufacturer</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<table class="zui-table">
    <form:form id="form" name="form" method="post" action="create" modelAttribute="manufacturer">
    <h1>Create manufacturer form</h1>
    <tr>
        <td>
            <p>Manufacturer title</p>
        </td>
        <td>
            <form:input type="text" path="title" id="title"/>
            <form:errors path="title" cssClass="error"/>
                ${message}
        </td>
    </tr>
</table>
<button type="submit" class="button">Create</button>
<div class="spacer"></div>
</form:form>
</body>
</html>