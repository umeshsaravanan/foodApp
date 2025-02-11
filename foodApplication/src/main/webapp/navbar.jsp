<%@page import="jakarta.servlet.http.Cookie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="navbar.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" />
</head>
<body>
	<%
		boolean isLoggedIn = false;
		Cookie[] cookieArray = request.getCookies();
		if(cookieArray != null)
	    for (Cookie cookie : cookieArray) {
	        if (cookie.getName().equals("name")) {
	            session.setAttribute("username", cookie.getValue());
	            isLoggedIn = true;
	        }
	        if (cookie.getName().equals("id")) {
	            session.setAttribute("userId", cookie.getValue());
	        }
	    }
	%>
	<div class="nav">
        <div class="left">
            <span class="app_name">
                <a href="home.jsp"><h1><strong>Z</strong>omato</h1></a>
            </span>
            <%--
	            <div class="search_div">
	                <input type="text" class="search" placeholder="Search...">
	            </div>
            --%>
        </div>
        <div class="right">
        	<a href="cartPage.jsp"><i class="fa-solid fa-cart-shopping"></i></a>
            <div class="<%= isLoggedIn ? "hide" : "buttons" %>">
                <a class="links" href="login.jsp">Log in</a>
            </div>
            <div class="<%= isLoggedIn ? "profile" : "hide" %>" id="drop_click" onclick="handleDropdown()">
                <img src="umesh.jpg" alt="">
                <p class="prof_name"><%= session.getAttribute("username") %> <i class="fa-solid fa-caret-down"></i></p>
            </div>
        </div>
    </div>
    <div class="drop_down" id="drop_down">
        <a>Profile</a>
        <a href="<%= isLoggedIn ? "logoutServlet" : "login.jsp" %>"><%= isLoggedIn ? "Log out" : "Log in" %></a>
        <a href="MyOrders">Orders</a>
    </div>

    <script>
        function handleDropdown(){
            const dropDown = document.getElementById("drop_down");

            if(dropDown.style.display === 'flex'){
                dropDown.style.display = 'none';
            }else{
                dropDown.style.display = 'flex';
            }
        }
    </script>
    
</body>
</html>