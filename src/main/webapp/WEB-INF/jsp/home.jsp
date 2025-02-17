<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Home - Online Quiz</title>
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
            display: block; /* Change from flex to block */
            max-width: 600px; /* Set a max-width for better readability */
            margin: auto; /* Center the content */
        }

        .categories, .quiz-results {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px; /* Adds spacing between sections */
        }

        .category-item {
            background-color: #4CAF50;
            color: white;
            padding: 15px;
            margin: 10px 0;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            display: block;
        }

        .category-item:hover {
            background-color: #45a049;
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

<!-- Include the navigation bar -->
<jsp:include page="navbar.jsp" />

<h1>Hello ${sessionScope.user.firstName}!</h1>

<div class="container">
    <!-- Quiz Categories Section -->
    <div class="categories">
        <h2>Start New Quiz</h2>

        <!-- Display categories dynamically -->
        <c:forEach var="category" items="${categories}">
            <a href="/quiz/start/${category.categoryId}" class="category-item">${category.name}</a>
        </c:forEach>
    </div>

    <!-- Recent Quiz Results Section -->
    <div class="quiz-results">
        <h2>Quiz History</h2>

        <c:if test="${empty recentQuizzes}">
            <p>You haven't taken any quizzes yet!</p>
        </c:if>

        <c:if test="${not empty recentQuizzes}">
            <table>
                <tr>
                    <th>Quiz Name</th>
                    <th>Date Taken</th>
                    <th>Result</th>
                </tr>
                <c:forEach var="quiz" items="${recentQuizzes}">
                    <tr>
                        <td>${quiz.name}</td>
                        <td>${quiz.timeEnd}</td>
                        <td>
                            <a href="/quiz/result/${quiz.quizId}" class="view-link">View Result</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>

</body>
</html>
