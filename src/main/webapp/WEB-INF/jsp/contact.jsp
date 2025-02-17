<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Contact Us</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        textarea {
            height: 150px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin-top: 15px;
        }

        button:hover {
            background-color: #45a049;
        }

        .message {
            text-align: center;
            margin-top: 15px;
            font-weight: bold;
        }

        .error {
            color: red;
        }

        .success {
            color: green;
        }
    </style>
</head>

<body>

<!-- Include the navigation bar -->
<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>Contact Us</h1>

    <form action="/contact" method="post">
        <label for="subject">Subject:</label>
        <input type="text" id="subject" name="subject" required>

        <label for="email">Your Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="message">Message:</label>
        <textarea id="message" name="message" required></textarea>

        <button type="submit">Send Message</button>
    </form>

    <!-- Display success or error messages -->
    <c:if test="${not empty success}">
        <p class="message success">${success}</p>
    </c:if>

    <c:if test="${not empty error}">
        <p class="message error">${error}</p>
    </c:if>
</div>

</body>
</html>
