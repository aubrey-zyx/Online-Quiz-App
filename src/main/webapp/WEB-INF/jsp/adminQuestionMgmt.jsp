<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Question Management</title>
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

        .add-btn {
            display: inline-block;
            margin-bottom: 15px;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
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
            padding: 8px 12px;
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

        .edit-btn {
            background-color: #ffc107;
            color: white;
        }

        .edit-btn:hover {
            background-color: #e0a800;
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>Question Management</h1>

    <a href="${pageContext.request.contextPath}/admin/questions/add" class="add-btn">+ Add New Question</a>

    <table>
        <thead>
        <tr>
            <th>Category</th>
            <th>Description</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="question" items="${questions}">
            <tr>
                <td>${question.category.name}</td>
                <td>${question.description}</td>
                <td class="${question.active ? 'active' : 'suspended'}">
                        ${question.active ? 'Active' : 'Suspended'}
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/questions/edit/${question.questionId}"
                       class="action-btn edit-btn">Edit</a>

                    <form action="${pageContext.request.contextPath}/admin/questions/toggle-status"
                          method="post" style="display:inline;">
                        <input type="hidden" name="questionId" value="${question.questionId}">
                        <button type="submit" class="action-btn ${question.active ? 'suspend-btn' : 'activate-btn'}">
                                ${question.active ? 'Suspend' : 'Activate'}
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
