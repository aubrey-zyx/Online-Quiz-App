<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Quiz Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
            text-align: center;
        }

        .container {
            max-width: 700px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            color: #333;
        }

        .pass {
            color: green;
            font-weight: bold;
            font-size: 20px;
        }

        .fail {
            color: red;
            font-weight: bold;
            font-size: 20px;
        }

        .question-card {
            background-color: #ffffff;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            text-align: left;
        }

        .question-text {
            font-weight: bold;
            font-size: 18px;
            color: #333;
            margin-bottom: 10px;
        }

        .choice {
            padding: 8px;
            border-radius: 5px;
            display: block;
            margin-bottom: 5px;
        }

        .correct-answer {
            background-color: #d4edda;
            border: 1px solid #155724;
            color: #155724;
            font-weight: bold;
        }

        .user-choice {
            background-color: #f8d7da;
            border: 1px solid #721c24;
            color: #721c24;
            font-weight: bold;
        }

        .highlight {
            font-weight: bold;
            color: #155724;
        }

        .incorrect {
            color: red;
        }

        .link {
            display: inline-block;
            margin-top: 20px;
            font-size: 16px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
        }

        .link:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>

<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>Quiz Result: ${quiz.name}</h1>
    <p><strong>User:</strong> ${user.firstName} ${user.lastName}</p>
    <p><strong>Start Time:</strong> ${quiz.timeStart}</p>
    <p><strong>End Time:</strong> ${quiz.timeEnd}</p>
    <p class="${isPassed ? 'pass' : 'fail'}">
        ${isPassed ? "Congratulations! You Passed!" : "Sorry, You Failed."}
    </p>

    <h2>Question Results</h2>

    <c:forEach var="result" items="${questionResults}">
        <div class="question-card">
            <p class="question-text">Q: ${result.question.description}</p>
            <c:forEach var="choice" items="${result.choices}">
                <p class="choice ${choice.choiceId == result.correctChoice.choiceId ? 'correct-answer' : (choice.choiceId == result.userChoice.choiceId ? 'user-choice' : '')}">
                        ${choice.description}
                </p>
            </c:forEach>
        </div>
    </c:forEach>

    <c:if test="${!isAdmin}">
        <a href="/home" class="link">Take Another Quiz</a>
    </c:if>
</div>

</body>
</html>
