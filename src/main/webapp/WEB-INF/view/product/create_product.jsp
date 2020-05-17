<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Create Plant</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<table class="zui-table">
    <form:form id="form" name="form" method="post" action="create" modelAttribute="product">
    <h1>Create plant form</h1>
    <tr>
        <td>
            <p>Plant title</p>
        </td>
        <td>
            <form:input type="text" path="title" id="title"/>
            <form:errors path="title" cssClass="error"/>
                ${message}
        </td>
    </tr>
    <tr>
        <td>
            <p>Plant price</p>
        </td>
        <td>
            <form:input path="price" id="price" onchange="validateNumber(this)"/>
            <script>
                var validNumber = new RegExp(/^\d*\.?\d*$/);
                var lastValid = document.getElementById("price").value;
                function validateNumber(elem) {
                    if (validNumber.test(elem.value)) {
                        lastValid = elem.value;
                    } else {
                        elem.value = lastValid;
                    }
                }
            </script>
            <form:errors path="price" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <td>
            <p>Plant manufacturer</p>
        </td>
        <td>
            <form:select path="manufacturer" items="${manufacturers}" itemValue="id" itemLabel="title"/>
            <form:errors path="manufacturer" cssClass="error"/>
        </td>
    </tr>
</table>
<button type="submit" class="button">Create</button>
<div class="spacer"></div>
</form:form>
</body>
</html>