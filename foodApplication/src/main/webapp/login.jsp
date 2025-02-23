<%@page import="java.net.URLDecoder"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    // Prevent browser caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setHeader("Expires", "0"); // Proxies.

    // Check for existing session or cookies
    boolean isLoggedIn = false;
    Cookie userNameCookie = null;
	Cookie[] cookieArray = request.getCookies();
	if(cookieArray != null)
    for (Cookie cookie : cookieArray) {
        if (cookie.getName().equals("name")) {
            userNameCookie = cookie;
            session.setAttribute("username", URLDecoder.decode(cookie.getValue(), "UTF-8"));
            isLoggedIn = true;
        }
        if(cookie.getName().equals("id")){
            session.setAttribute("userId", cookie.getValue());
        }
    }
	Object fromOrders = session.getAttribute("fromMyOrders");
	if(isLoggedIn && fromOrders != null && (Boolean)fromOrders){
		session.setAttribute("fromMyOrders",false);
		request.getRequestDispatcher("MyOrders").forward(request, response);
		return;
	} else if (isLoggedIn) {
        response.sendRedirect("home.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
	<div class="login-container">
		<form action="loginServlet" method="post" class="login-form">
			<h2>Login</h2>
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" placeholder="Enter your username"
					required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter your password"
					required>
			</div>
			<div class="form-group">
				<button type="submit" class="login-btn">Login</button>
			</div>

			<p>
				Don't have an Account? <a href="register.jsp">Register Here</a>
			</p>
		</form>
	</div>
</body>
</html>
