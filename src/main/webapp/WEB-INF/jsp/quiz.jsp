<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
        }

        h1 {
            color: #333;
        }

        .quiz-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
        }

        .question {
            margin-bottom: 20px;
        }

        .question p {
            font-size: 18px;
            font-weight: bold;
        }

        .choices label {
            display: block;
            margin-bottom: 8px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<!-- Include navigation bar -->
<jsp:include page="navbar.jsp" />

<div class="quiz-container">
    <h1>Quiz</h1>

    <form action="/quiz/submit" method="post">
        <input type="hidden" name="quizId" value="${quizId}"/>

        <c:forEach var="question" items="${questions}" varStatus="loop">
            <div class="question">
                <p>Q${loop.index + 1}: ${question.description}</p>
                <input type="hidden" name="questionIds" value="${question.questionId}"/>

                <div class="choices">
                    <c:forEach var="choice" items="${choiceService.getChoicesForQuestion(question.questionId)}">
                        <label>
                            <input type="radio" name="userChoices_${question.questionId}" value="${choice.choiceId}" required/>
                                ${choice.description}
                        </label>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

        <button type="submit">Submit Quiz</button>
    </form>
</div>

</body>
</html>
