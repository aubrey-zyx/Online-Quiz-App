<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz Result Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
        }

        h1, h2 {
            color: #333;
        }

        .container {
            max-width: 800px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        form {
            margin-bottom: 20px;
        }

        select, button {
            padding: 10px;
            margin-right: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .view-link {
            color: #4CAF50;
            text-decoration: none;
        }

        .view-link:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<jsp:include page="navbar.jsp" />

<h1>Quiz Result Management</h1>

<div class="container">
    <!-- Filter Section -->
    <form method="GET" action="${pageContext.request.contextPath}/admin/quiz-results">
        <label for="category">Filter by Category:</label>
        <select name="categoryId" id="category">
            <option value="">All Categories</option>
            <c:forEach var="category" items="${categories}">
                <option value="${category.categoryId}" ${category.categoryId == selectedCategoryId ? 'selected' : ''}>
                        ${category.name}
                </option>
            </c:forEach>
        </select>

        <label for="user">Filter by User:</label>
        <select name="userId" id="user">
            <option value="">All Users</option>
            <c:forEach var="user" items="${users}">
                <option value="${user.id}" ${user.id == selectedUserId ? 'selected' : ''}>
                        ${user.firstName} ${user.lastName}
                </option>
            </c:forEach>
        </select>

        <button type="submit">Apply Filters</button>
    </form>

    <!-- Quiz Results Table -->
    <table>
        <thead>
        <tr>
            <th>Taken Time</th>
            <th>Category</th>
            <th>User</th>
            <th>No. of Questions</th>
            <th>Score</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="quiz" items="${quizResults}">
            <tr>
                <td>${quiz.timeEnd}</td>
                <td>${quiz.category.name}</td>
                <td>${quiz.user.firstName} ${quiz.user.lastName}</td>
                <td>${quiz.questions.size()}</td>
                <td>${quiz.score}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/quiz/result/${quiz.quizId}" class="view-link">
                        View Result
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
