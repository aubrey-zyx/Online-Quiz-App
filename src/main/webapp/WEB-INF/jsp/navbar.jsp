<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
    <ul style="list-style: none; display: flex; gap: 20px;">
        <!-- Show Home if the user IS logged in -->
        <c:if test="${not empty sessionScope.user}">
            <li>
                <c:choose>
                    <c:when test="${sessionScope.user.admin}">
                        <a href="${pageContext.request.contextPath}/admin">Admin Home</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:if>

        <c:if test="${!sessionScope.user.admin}">
            <li><a href="/contact">Contact Us</a></li>
        </c:if>

        <!-- Show Login if the user is NOT logged in -->
        <c:if test="${empty sessionScope.user}">
            <li><a href="/login">Login</a></li>
        </c:if>

        <!-- Show Logout if the user IS logged in -->
        <c:if test="${not empty sessionScope.user}">
            <li><a href="/logout">Logout</a></li>
        </c:if>

        <!-- Always show Register -->
        <li><a href="/register">Register</a></li>
    </ul>
</nav>