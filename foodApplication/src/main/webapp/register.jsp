<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="register.css">
</head>
<body>
    <div class="register-container">
    <form action="RegisterServlet" method="post" class="register-form">
        <h2>Register</h2>

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="retypePassword">Retype Password</label>
            <input type="password" id="retypePassword" name="retypePassword" required>
        </div>

        <button type="submit" class="register-btn">Register</button>

        <p>Already have an account? <a href="login.jsp">Login here</a></p>
        
    </form>
</div>

</body>
</html>
