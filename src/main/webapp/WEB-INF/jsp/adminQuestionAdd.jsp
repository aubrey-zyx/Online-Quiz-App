<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Question</title>
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

        .add-choice-btn {
            background-color: #007bff;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
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

    <script>
        function addChoiceField() {
            const choiceContainer = document.getElementById("choice-container");
            const choiceCount = choiceContainer.children.length;
            const choiceHtml = `
                <div>
                    <input type="text" name="choices" placeholder="Enter choice ${choiceCount + 1}" required />
                    <input type="radio" name="correctChoice" value="${choiceCount}" required /> Correct Answer
                </div>`;
            choiceContainer.insertAdjacentHTML("beforeend", choiceHtml);
        }
    </script>
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>Add New Question</h1>

    <form action="${pageContext.request.contextPath}/admin/questions/add" method="post">
        <label for="description">Question Description:</label>
        <textarea id="description" name="description" rows="3" required></textarea>

        <label for="category">Category:</label>
        <select id="category" name="categoryId" required>
            <c:forEach var="category" items="${categories}">
                <option value="${category.categoryId}">${category.name}</option>
            </c:forEach>
        </select>

        <label>Choices:</label>
        <div id="choice-container" class="choice-container">
            <div>
                <input type="text" name="choices" placeholder="Enter choice 1" required />
                <input type="radio" name="correctChoice" value="0" required /> Correct Answer
            </div>
        </div>
        <button type="button" class="add-choice-btn" onclick="addChoiceField()">+ Add Another Choice</button>

        <button type="submit" class="submit-btn">Submit Question</button>
    </form>
</div>

</body>
</html>
