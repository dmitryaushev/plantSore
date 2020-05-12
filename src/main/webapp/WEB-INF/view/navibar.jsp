<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Manufacturers
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/manufacturer/showManufacturers">Show All</a>
            <a href="${pageContext.request.contextPath}/manufacturer/findManufacturer">Find</a>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="${pageContext.request.contextPath}/manufacturer/createManufacturer">Create</a>
            </security:authorize>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Plants
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/plant/showPlants">Show all</a>
            <a href="${pageContext.request.contextPath}/plant/findPlant">Find</a>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="${pageContext.request.contextPath}/plant/createPlant">Create</a>
            </security:authorize>
        </div>
    </div>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <div class="dropdown">
            <button class="dropbtn">Users
                <i></i>
            </button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/user/showUsers">Show all</a>
                <a href="${pageContext.request.contextPath}/user/findUser">Find</a>
                <a href="${pageContext.request.contextPath}/user/createUser">Create</a>
            </div>
        </div>
    </security:authorize>
    <div class="dropdown" style="float: right">
        <button class="dropbtn"><security:authentication property="principal.name"/>
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/user/get?id=
<security:authentication property="principal.id"/>">Details</a>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
</div>
