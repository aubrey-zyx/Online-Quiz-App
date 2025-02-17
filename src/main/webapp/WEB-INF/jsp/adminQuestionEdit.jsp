<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Question</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
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

        label {
            font-weight: bold;
            display: block;
            margin: 10px 0 5px;
        }

        input, select, textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .choice-container {
            margin-bottom: 15px;
        }

        .submit-btn {
            background-color: #28a745;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>Edit Question</h1>

    <form action="${pageContext.request.contextPath}/admin/questions/edit" method="post">
        <input type="hidden" name="questionId" value="${question.questionId}"/>

        <label for="description">Question Description:</label>
        <textarea id="description" name="description" rows="3" required>${question.description}</textarea>

        <label for="category">Category:</label>
        <select id="category" name="categoryId" required>
            <c:forEach var="category" items="${categories}">
                <option value="${category.categoryId}" ${category.categoryId == question.category.categoryId ? 'selected' : ''}>
                        ${category.name}
                </option>
            </c:forEach>
        </select>

        <label>Choices:</label>
        <div id="choice-container" class="choice-container">
            <c:forEach var="choice" items="${choices}" varStatus="loop">
                <div>
                    <input type="text" name="choices" value="${choice.description}" required />
                    <input type="radio" name="correctChoice" value="${loop.index}" ${choice.correct ? 'checked' : ''} required /> Correct Answer
                </div>
            </c:forEach>
        </div>

        <button type="submit" class="submit-btn">Update Question</button>
    </form>
</div>

</body>
</html>
