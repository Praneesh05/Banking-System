<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>STUDENT SIGN UP PAGE</title>
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
        width: 100%;
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
    select {
    width: calc(100% - 120px); /* Same width as other input fields */
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1em;
}
    
</style>
</head>
<body>
    <div class="heading">
        <header><h1>REGISTER PAGE</h1></header>
    </div>

    <form action="SigninServlet" method="post">
        <div class="login">
            <h2>SIGN UP</h2>
            
            <div class="form-group">
                <label for="name"><b>NAME</b></label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email"><b>EMAIL</b></label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="intdeposite"><b>Initial Deposit</b></label>
                <input type="text" id="intdeposite" name="intdeposite" required pattern="\d+(\.\d{1,2})?" title="Please enter a valid amount.">
                
            </div>
            
            <div class="form-group">
            
            <label for="gender"><b>GENDER</b></label>
                
                <select name="gender">
                	
		            <option value="male">MALE</option>
		            <option value="female">FEMALE</option>
		            <option value="other">OTHER</option>
        		</select>
            </div>
            
            <div class="form-group">
                <label for="number"><b>NUMBER</b></label>
                <input type="text" id="number" name="number" required pattern="\d{10}" title="Please enter a valid 10-digit phone number.">
            </div>
            <div class="form-group">
                <label for="password"><b>PASSWORD</b></label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="conpassword"><b>CONFIRM PASSWORD</b></label>
                <input type="password" id="conpassword" name="conpassword" required>
            </div>
            
            <div id="button">
                <button type="submit">REGISTER</button>
                <button type="button" onclick="location.href='Welcome_page.jsp'">Home Page</button>
            </div>
        </div>
    </form>
    <div class="footer-bottom">
                <p>&copy; 2024 SecureBank. All rights reserved.</p>
            </div>
    <script>
    document.querySelector("form").addEventListener("submit", function(e) {
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("conpassword").value;
        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            e.preventDefault();
        }
    });
</script>
    
</body>
</html>
