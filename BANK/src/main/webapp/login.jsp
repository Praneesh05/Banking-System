<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN_LOGIN_PAGE</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .login {
        background-color: white;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
        width: 350px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .login h2 {
        text-align: center;
        color: #0a0a0a;
        margin-bottom: 20px;
    }

    .form-group {
        margin-bottom: 15px;
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    label {
        width: 100px;
        font-size: 1.1em;
        color: #070707;
    }

    input[type="email"], input[type="password"], input[type="text"] {
        width: calc(100% - 120px);
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 1em;
    }
	#home{
	text-align:center;
	display:flex;
	color:black;
	}
    #button {
        text-align: center;
        display: flex;
        justify-content: space-between;
    }

    button {
        padding: 10px 20px;
        background-color: #3815c7;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 1em;
        cursor: pointer;
        width: 120%;
        margin:10px;
    }

    button:hover {
        background-color: #1e0e98;
    }

    .heading {
        margin-bottom: 20px;
        text-align: center;
    }

    .heading h1 {
        font-size: 2em;
        color: #333;
    }
</style>
</head>
<body>

	<div class="heading">
        <header><h1>LOGIN ACCOUNT</h1></header>
    </div>

    <form action="LoginServlet" method="post">
    
    <input type="hidden" name="userType" id="userType" value="user">
    
    <div class="login">
    	
        <h2>LOGIN</h2>
        <div class="form-group">
            <label for="email"><b>EMAIL:</b></label>
            <input type="email" id="emai" name="email" required>
        </div>
        
        <div class="form-group">
            <label for="password"><b>PASSWORD:</b></label>
            <input type="password" id="password" name="password" required>
        </div>
        <div id="button">
            <button type="submit">LOGIN</button>
            <button type="button" onclick="location.href='signin.jsp'">New User?</button>
        </div>
        <div id="home">
        <button type="button" onclick="location.href='Welcome_page.jsp'">Home Page</button>
    </div>
    </div>
	</form>
	
	<div class="footer-bottom">
                <p>&copy; 2024 SecureBank. All rights reserved.</p>
           </div>
</body>
</html>