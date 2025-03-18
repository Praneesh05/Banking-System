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
    <title>Transaction History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 60%;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            margin-top: 30px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        label {
            font-size: 18px;
            margin-bottom: 10px;
            color: #333;
        }

        select {
            width: 50%;
            padding: 10px;
            font-size: 16px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #fafafa;
        }
		.buttons{
		display:flex;
		gap:10px;}
        button {
            padding: 12px 24px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #45a049;
        }

        .form-group {
            margin-bottom: 20px;
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Transaction History</h2>
        <form action="TransactionServlet" method="get">
            <input type="hidden" name="email" value="<%= email %>"> <!-- Pass email to servlet -->

            <div class="form-group">
                <label for="transaction"><b>Choose Your Need:</b></label>
                <select name="action" id="transaction">
                    <option value="Show All Transaction">View All Transactions</option>
                    <option value="transactionmade">Transaction Made</option>
                    <option value="transactionreceived">Transaction Received</option>
                    <optgroup label="Transaction Type">
                        <option value="withdrawal">Withdraw</option>
                        <option value="deposit">Deposit</option>
                    </optgroup>
                </select>
            </div>
			<div class="buttons">
            <button type="submit">Submit</button>
          	<button type="button" onclick="location.href='options.jsp'">Profile Page</button>
            </div>
            
        </form>
        <div >
                <p align=center>&copy; 2024 SecureBank. All rights reserved.</p>
           </div>
    </div>
</body>
</html>

<%
    }
%>
