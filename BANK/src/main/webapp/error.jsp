<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        h1 {
            font-size: 24px;
            color: #dc3545;
            margin-bottom: 20px;
        }
        p {
            font-size: 16px;
            color: #333;
        }
        a {
            text-decoration: none;
            color: #007bff;
            font-size: 16px;
            margin-top: 20px;
            display: inline-block;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Transaction Failed</h1>

        <c:choose>
            <c:when test="${param.error == 'InvalidAmount'}">
                <p>Error: Invalid amount entered. Please enter a valid number.</p>
            </c:when>
            <c:otherwise>
                <p>Sorry, there was an issue processing your transaction. Please try again later.</p>
            </c:otherwise>
        </c:choose>

        <a href="options.jsp">Go Back to Home Page</a>
    </div>
</body>
</html>
