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
    <title>View Balance</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
        }
        label {
            display: block;
            font-size: 16px;
            margin-bottom: 10px;
        }
        input[type="text"],input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 16px;
        }
        .buttons{
        display:flex;
        gap:10px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>View Balance</h1>
        <form action="AccountServlet" method="GET">
            <input type="hidden" name="action" value="viewBalance">
            <input type="hidden" name="email" value="<%= email %>">
            
            <label for="account_no">Account Number:</label>
            <input type="text" name="account_no" id="account_no" required>
            
            <label for="password">PassWord:</label>
            <input type="password" name="password" id="password" required>
            
            <div class="buttons">
            <button type="submit">View Balance</button>
          	<button type="button" onclick="location.href='options.jsp'">Profile Page</button>
            </div>
            
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
