<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.foodApp.models.Menu"%>
<%@page import="com.foodApp.models.CartItem"%>
<%@page import="com.foodApp.models.Restaurant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
 	Restaurant currentRestaurant = (Restaurant)request.getAttribute("currentRestaurant");
 	List<Menu> currentMenu = (List<Menu>)request.getAttribute("menuItems");
 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hotel | <%= currentRestaurant.getRestaurantName() %></title>
<link rel="stylesheet" href="restaurant.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<header>
		<img
			src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(currentRestaurant.getRestaurantImage()) %>"
			alt="">
		<div class="overLayText">
			<div class="top">
				<a href="home.jsp"><i class="fa-solid fa-arrow-left"
					style="color: #ffffff;"></i></a>
				<h1><%= currentRestaurant.getRestaurantName() %></h1>
			</div>
			<div class="bottom">
				<div>
					<h3>
						<i class="fa-solid fa-location-dot" style="color: #ff0000;"></i>
						<%= currentRestaurant.getLocation() %></h3>
					<p>
						Delivery in
						<%= currentRestaurant.getDeliveryTime() %>
						mins
					</p>
				</div>
				<div
					class='rating_card <%= (currentRestaurant.getRating() >= 4) ? "green" : (currentRestaurant.getRating() >= 2) ? "orange" : "red"%> '>
					<p>
						<% out.println(currentRestaurant.getRating()); %>
						<i class="fa-solid fa-star" style="color: #ffffff;"></i>
					</p>
				</div>
			</div>
		</div>
	</header>

	<div class="menus">
		<% for(Menu m : currentMenu){ %>
		<% if(m.isAvailable()) { %>
		<div class="menu">
			<div class="item_details">
				<div class="item_name">
					<i class="fa-solid fa-circle"
						style="color:<%= m.isVeg() ? "green" : "red"%>;borderColor:<%= m.isVeg() ? "green" : "red"%>"></i>
					<h1 class="item_name"><%= m.getItemName() %></h1>
				</div>
				<p>
					&#8377;
					<%= m.getPrice() %></p>
				<p class="item_desc"><%= m.getDescription() %></p>
			</div>
			<div class="item_img">
				<img src="umesh.jpg">
				<div class="inc_dec">
					<button type="button"
						onclick="handleAdd(this,<%= m.getMenuId() %>)">ADD</button>
					<div style="display: none;" class="counter-controls">
						<button class="action_btn" type="button"
							onclick="updateCounter(this, -1,<%= m.getMenuId() %>)">-</button>
						<span class="counter">1</span>
						<button class="action_btn" type="button"
							onclick="updateCounter(this, 1,<%= m.getMenuId() %>)">+</button>
					</div>
				</div>
			</div>
		</div>
		<% } %>
		<% } %>
	</div>

	<%@ include file="cart_card.jsp"%>

	<script type="text/javascript" src="counter.js"></script>
</body>
</html>