<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us Management</title>
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
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
            text-align: center;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .view-message {
            display: inline-block;
            padding: 6px 12px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
        }

        .view-message:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>Contact Us Messages</h1>

    <table>
        <thead>
        <tr>
            <th>Subject</th>
            <th>Email</th>
            <th>Time</th>
            <th>Message</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td>${contact.subject}</td>
                <td>${contact.email}</td>
                <td>${contact.time}</td>
                <td>${contact.message}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
