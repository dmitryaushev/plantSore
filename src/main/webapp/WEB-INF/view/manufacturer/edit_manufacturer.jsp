<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form id="form" name="form" method="post" action="editManufacturer" modelAttribute="manufacturer">
    <table class="zui-table">
        <form:hidden path="id" id="id"/>
        <form:hidden path="products" id="products"/>
        <h1>Edit manufacturer form</h1>
        <tr>
            <td>
                <p>Title</p>
            </td>
            <td>
                <form:input type="text" path="title" id="title"/>
                <form:errors path="title" cssClass="error"/>
                    ${message}
            </td>
        </tr>
    </table>
    <button type="submit" class="button">Save</button>
    <a href="${pageContext.request.contextPath}/manufacturer/delete?id=${manufacturer.id}"
       class="button" role="button" tabindex="0">Delete Manufacturer</a>
    <div class="spacer"></div>
</form:form>
</body>
</html>