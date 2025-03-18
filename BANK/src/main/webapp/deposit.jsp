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
    <title>Deposit Money</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
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
            text-align: left;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
        }
        label {
            font-size: 16px;
            color: #333;
            display: block;
            margin-top: 10px;
        }
        input[type="text"],
        input[type="number"],input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .buttons{
        display:flex;
        gap:10px;}
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Deposit Money</h1>
        <form action="AccountServlet" method="POST">
            <input type="hidden" name="action" value="deposit">
            <input type="hidden" name="email" value="<%= email %>">

            <label for="account_no">Account Number:</label>
            <input type="text" name="account_no" id="account_no" required>

            <label for="amount">Amount:</label>
            <input type="number" name="amount" id="amount" required>

			<label for="password">PassWord:</label>
            <input type="password" name="password" id="password" required>
            <div class="buttons">
            <button type="submit">Deposit</button>
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
