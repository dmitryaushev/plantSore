<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form id="form" name="form" method="post" action="editProduct" modelAttribute="product">
    <table class="zui-table">
        <form:hidden path="id" id="id"/>
        <h1>Edit plant form</h1>
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
        <tr>
            <td>
                <p>Price</p>
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
                <form:errors path="title" cssClass="error"/>
            </td>
        </tr>
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
    <button type="submit" class="button">Save</button>
    <a href="${pageContext.request.contextPath}/plant/delete?id=${product.id}"
       class="button" role="button" tabindex="0">Delete Plant</a>
    <div class="spacer"></div>
</form:form>
</body>
</html>