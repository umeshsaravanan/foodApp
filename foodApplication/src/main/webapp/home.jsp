<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="java.net.ResponseCache"%>
<%@page import="com.mysql.cj.callback.UsernameCallback"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="com.foodApp.DAOImplementation.RestaurantDAOImpl"%>
<%@page import="com.foodApp.models.Restaurant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>

	<%@ include file="navbar.jsp"%>

	<% 
		if(request.getAttribute("restaurants") == null){
			request.getRequestDispatcher("HomeServlet").forward(request, response);
			return;			
		}
	%>
	<div class="restaurants">
		<% for(Restaurant r : (List<Restaurant>)request.getAttribute("restaurants")){ %>
		<a href="MenuServlet?id=<%= r.getRestaurantId() %>"
			class="restaurant_card">
			<div class="restaurant_img">
				<img
					src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(r.getRestaurantImage()) %>"
					alt="">
				<div class="img_overlay"></div>
			</div>
			<div class="restaurant_details">
				<div style="display: flex; justify-content: space-between;">
					<div style="display: flex; flex-direction: column; gap: .5rem;">
						<h2>
							<% out.println(r.getRestaurantName()); %>
						</h2>
						<p>
							<i class="fa-solid fa-location-dot" style="color: #ff0000;"></i>
							<% out.println(r.getLocation()); %>
						</p>
					</div>
					<div
						class='rating_card <%= (r.getRating() >= 4) ? "green" : (r.getRating() >= 2) ? "orange" : "red"%> '>
						<p>
							<% out.println(r.getRating()); %>
							<i class="fa-solid fa-star" style="color: #ffffff;"></i>
						</p>
					</div>
				</div>
				<div>
					<p>
						<i class="fa-solid fa-kitchen-set" style="color: #000000;"></i>Cuisine:
						<% out.println(r.getCuisine()); %>
					</p>
					<br>
					<p>
						Usually Delivered in
						<% out.println(r.getDeliveryTime()); %>
						minutes
					</p>
				</div>
			</div>
		</a>
		<%} %>
	</div>


	<%@ include file="cart_card.jsp"%>
</body>
</html>