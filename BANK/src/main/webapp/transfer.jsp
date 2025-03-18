<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*" %>
<%
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp");
    } else {
        String email = (String) session.getAttribute("email");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transfer Money</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f8fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 40px;
            max-width: 400px;
            width: 100%;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }
        label {
            font-size: 16px;
            display: block;
            margin-bottom: 8px;
        }
        input[type="text"], input[type="number"],input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 16px;
        }
        button {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Transfer Money</h1>
        <form action="AccountServlet" method="POST">
            <input type="hidden" name="action" value="transfer">
            <input type="hidden" name="email" value="<%= email %>">
            
            <label for="from_account">From Account:</label>
            <input type="text" name="from_account" id="from_account" required>
            
            <label for="to_account">To Account:</label>
            <input type="text" name="to_account" id="to_account" required>
            
            <label for="amount">Amount:</label>
            <input type="number" name="amount" id="amount" required>
            
            <label for="password">PassWord:</label>
            <input type="password" name="password" id="password" required>
            <button type="submit">Transfer</button>
            <button type="button" onclick="location.href='options.jsp'">Profile Page</button>
            
        </form>
        <div >
                <p>&copy; 2024 SecureBank. All rights reserved.</p>
           </div>
    </div>
    
</body>
</html>

<%
    }
%>
