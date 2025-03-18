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
    <title>View Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            width: 100%;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
        }
        p {
            font-size: 14px;
            color: #333;
            text-align: center;
        }
        label {
            font-size: 16px;
            margin-bottom: 10px;
            display: block;
            color: #333;
        }
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        .buttons{
        	display:flex;
        	gap:10px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        button{
        background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        button:hover {
            background-color: #45a049;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <p>Logged in as: <strong><%= email %></strong></p>
        <h1>Enter Email ID</h1>
        <form action="AccountServlet" method="GET">
            <input type="hidden" name="action" value="viewAccount">
            <input type="hidden" name="email" value="<%= email %>">
            
            
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required>
            <div class="buttons">
            	<input type="submit" value="View Account">
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
