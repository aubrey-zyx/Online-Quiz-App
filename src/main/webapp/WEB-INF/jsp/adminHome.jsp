<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
            text-align: center;
        }

        .container {
            max-width: 600px;
            background-color: #fff;
            margin: 50px auto;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        .admin-buttons {
            display: flex;
            flex-direction: column;
            gap: 15px;
            margin-top: 20px;
        }

        .admin-buttons a {
            display: block;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            padding: 12px;
            border-radius: 5px;
            font-size: 16px;
            text-align: center;
            transition: background-color 0.3s ease-in-out;
        }

        .admin-buttons a:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>

<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>Admin Dashboard</h1>
    <p>Hello ${sessionScope.user.firstName} (Admin)</p>

    <div class="admin-buttons">
        <a href="${pageContext.request.contextPath}/admin/users">User Management</a>
        <a href="${pageContext.request.contextPath}/admin/quiz-results">Quiz Result Management</a>
        <a href="${pageContext.request.contextPath}/admin/questions">Question Management</a>
        <a href="${pageContext.request.contextPath}/admin/contacts">Contact Us Management</a>
    </div>
</div>

</body>
</html>
