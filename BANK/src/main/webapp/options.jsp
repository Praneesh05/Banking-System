<%@ page import="java.sql.*, jakarta.servlet.http.*, jakarta.servlet.*" %>
<%
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp");  // Redirect to login if session is invalid
    } else {
        String email = (String) session.getAttribute("email");  // Retrieve email from session
        
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Banking System</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            color: #333;
        }

        /* Container for main content */
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            padding: 20px;
        }

        /* Header styles */
        header {
            background-color: #005b96;
            color: white;
            padding: 20px;
            width: 100%;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo-container {
            display: flex;
            align-items: center;
        }

        .logo {
            width: 60px;
            margin-right: 15px;
        }

        /* Right aligned buttons */
        .redirect {
            display: flex;
            gap: 20px;
        }
        
        .opt {
            text-decoration: none;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            padding: 10px 20px;
            text-align: center;
            transition: 0.3s;
            color: #333;
        }

        .opt:hover {
            background-color: #fff;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
        }

        /* Content styles */
        .content {
            margin-top: 30px;
        }

        h2 {
            color: #005b96;
        }

        /* Options styles */
        .options {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            margin-top: 40px;
        }

        .option {
            text-decoration: none;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 160px;
            text-align: center;
            transition: 0.3s;
        }

        .option img {
            width: 50px;
            margin-bottom: 15px;
        }

        .option span {
            display: block;
            font-size: 1.2em;
            color: #333;
        }

        .option:hover {
            transform: scale(1.05);
            background-color: #005b96;
            color: white;
        }

        .option:hover img {
            filter: brightness(0) invert(1);
        }

        /* Footer styles */
        footer {
            margin-top: 50px;
            background-color: #333;
            color: white;
            padding: 15px;
            width: 100%;
            position: fixed;
            bottom: 0;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Header Section -->
        <header>
            <div class="logo-container">
                <img src="logo.webp" alt="Bank Logo" class="logo">
                <h1>Secure Bank</h1>
            </div>
            
            <div class="redirect">
                <a href="Welcome_page.jsp" class="opt">
                    <span>Home</span>
                </a>
                <a href="login.jsp" class="opt">
                    <span>Logout</span>
                </a>
            </div>
        </header>

        <!-- Main Content Section -->
        <div class="content">
            <h2>Welcome to Secure Bank</h2>
            <p>Your trusted financial partner for secure transactions.</p>
            
            <!-- Navigation Menu for Banking Options -->
            <div class="options">
                <a href="account.jsp" class="option">
                    <img src="account.jpeg" alt="Account Details">
                    <span>Account Details</span>
                </a>
                <a href="transfer.jsp" class="option">
                    <img src="transder.jpeg" alt="Transfer">
                    <span>Transfer</span>
                </a>
                <a href="withdraw.jsp" class="option">
                    <img src="withdraw.jpeg" alt="Withdraw">
                    <span>Withdraw</span>
                </a>
                <a href="viewbalance.jsp" class="option">
                    <img src="balance.jpeg" alt="View Balance">
                    <span>View Balance</span>
                </a>
                <a href="deposit.jsp" class="option">
                    <img src="deposit.jpeg" alt="Deposit">
                    <span>Deposit</span>
                </a>
                <a href="transaction.jsp" class="option">
                    <img src="deposit.jpeg" alt="transaction">
                    <span>Transaction</span>
                </a>
            </div>
        </div>

        <!-- Footer Section -->
        <div >
                <p>&copy; 2024 SecureBank. All rights reserved.</p>
           </div>
    </div>
</body>
</html>
