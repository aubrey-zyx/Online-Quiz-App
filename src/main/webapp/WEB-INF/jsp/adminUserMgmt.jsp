<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 900px;
            margin: 40px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            margin-top: 20px;
        }

        th, td {
            padding: 15px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .active {
            color: #28a745;
            font-weight: bold;
        }

        .suspended {
            color: #dc3545;
            font-weight: bold;
        }

        .action-btn {
            padding: 8px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }

        .suspend-btn {
            background-color: #dc3545;
            color: white;
        }

        .suspend-btn:hover {
            background-color: #c82333;
        }

        .activate-btn {
            background-color: #28a745;
            color: white;
        }

        .activate-btn:hover {
            background-color: #218838;
        }

        /* Responsive Design */
        @media screen and (max-width: 768px) {
            .container {
                width: 90%;
            }

            table {
                font-size: 14px;
            }

            th, td {
                padding: 10px;
            }

            .action-btn {
                font-size: 12px;
                padding: 6px 12px;
            }
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>User Management</h1>

    <table>
        <thead>
        <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.email}</td>
                <td class="${user.active ? 'active' : 'suspended'}">
                        ${user.active ? 'Active' : 'Suspended'}
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/users/toggle-status" method="post">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit" class="action-btn ${user.active ? 'suspend-btn' : 'activate-btn'}">
                                ${user.active ? 'Suspend' : 'Activate'}
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
