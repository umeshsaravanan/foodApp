<%@page import="java.util.Base64"%>
<%@page import="com.foodApp.models.Restaurant"%>
<%@page import="com.foodApp.models.Menu"%>
<%@page import="com.foodApp.DAOImplementation.RestaurantDAOImpl"%>
<%@page import="com.foodApp.models.Orders"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.Duration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
<link rel="stylesheet" type="text/css" href="orders.css">
</head>
<body>
	<%
		Menu menu = (Menu)session.getAttribute("item");
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		if(menu == null || restaurant == null){
			request.getRequestDispatcher("MyOrders").forward(request,response);
			return;
		}
	%>
	<%@ include file="navbar.jsp"%>
	<div class="content">

		<div class="left-section">
			<h2>Favorites</h2>
			<p>Add your favorite hotels and food here...</p>
			<div class="imgContainer">
				<img
					src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(restaurant.getRestaurantImage()) %>"
					alt="">
				<div class="favorites-box">
					<h3>
						Frequently Ordered <span><%= menu.getItemName() %></span>
					</h3>
					<p>
						From <span><%= restaurant.getRestaurantName() %></span>
					</p>
				</div>
			</div>

			<h3>
				Total Spent : &#8377;<%= session.getAttribute("total") %></h3>
		</div>

		<div class="right-section">
			<h3>Your Orders</h3>

			<% 
	            List<Orders> orders = (List<Orders>) session.getAttribute("orders");
	        	if(orders == null){
	        		System.out.println("inside");
	    			request.getRequestDispatcher("MyOrders").forward(request, response);
	        	}
	        	
	            RestaurantDAOImpl restaurantDao = new RestaurantDAOImpl();
	            int totalOrder = 0;
	            LocalDateTime now = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	
	            for (Orders order : orders) {
	                LocalDateTime orderDateTime = LocalDateTime.parse(order.getOrderDate().toString(), formatter);
	                Duration timeDiff = Duration.between(orderDateTime, now);
	                boolean isRecentOrder = timeDiff.toMinutes() <= 60 && now.toLocalDate().equals(orderDateTime.toLocalDate());
	        %>

			<div class="ordersCard">
				<p>
					Order From:
					<%= restaurantDao.get(order.getRestaurantId()).getRestaurantName() %></p>
				<p>
					&#8377;
					<%= order.getTotalAmount() %></p>

				<% if (isRecentOrder) { %>
				<p class="order-now">Ordered now</p>
				<% } else { %>
				<p>
					Ordered on:
					<%= order.getOrderDate() %></p>
				<% } %>

				<% totalOrder += order.getTotalAmount(); %>
			</div>

			<% } %>
		</div>
	</div>
</body>
</html>
