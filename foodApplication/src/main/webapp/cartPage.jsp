<%@page import="java.util.Map.Entry"%>
<%@page import="com.foodApp.models.CartItem"%>
<%@page import="java.util.Map"%>
<%@page import="com.foodApp.models.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="cartPage.css">
</head>
<body>
	<%@ include file="navbar.jsp" %>
	
	<% 
		Cart myCart = (Cart)request.getSession().getAttribute("cart");
	%>
	
	<% if(myCart != null && myCart.getSize() > 0){ %>
		<div class="cart_payment">
			<div class="cart_items">
			<div class="cart-header">
				<h3 class="cart_heading">Items in Cart</h3>
				<button onclick="clearCart()" type="button">Clear Cart</button>
			</div>
			<% for(Map.Entry<Integer, CartItem> cartItem : (myCart.getCart()).entrySet()){ %>
				<% CartItem currentItem = cartItem.getValue(); %>
				<div class="cart_item" data-id="<%= currentItem.getMenuId() %>">
		            <div class="item_details">
		                <div class="item_name">
		                    <h1 class="item_name"><%= currentItem.getItemName() %></h1>
		                </div>
		                <p>&#8377; <%= currentItem.getPrice() %></p>
						<p id="total_<%= currentItem.getItemName().replaceAll("\\s+", "_") %>">
					        Total: &#8377; <%= currentItem.getPrice() * currentItem.getQuantity() %>
					    </p>
		            </div>
		            <div class="item_img">
		                <img src="umesh.jpg">
		                <div class="inc_dec">
		                    <div class="counter-controls">
		                        <button class="action_btn" type="button" onclick="updateCounter(this, -1,<%= currentItem.getMenuId() %>,true)" data-item-name="<%= currentItem.getItemName() %>" data-item-price="<%= currentItem.getPrice() %>">-</button>
		                        <span class="counter"><%= currentItem.getQuantity() %></span>
		                        <button class="action_btn" type="button" onclick="updateCounter(this, 1,<%= currentItem.getMenuId() %>,true)" data-item-name="<%= currentItem.getItemName() %>" data-item-price="<%= currentItem.getPrice() %>">+</button>
		                    </div>
		                </div>
						<i class="fa-solid fa-trash delete" onclick="deleteItem(<%= currentItem.getMenuId() %>, '<%= currentItem.getItemName() %>')" title="remove"></i>
		            </div>
        		</div>
			<%} %>			
			</div>
		<div class="payment_section">
			<div class="amount">
				<table style="border-spacing: 0 10px; border-collapse: separate;">
					<tr>
						<th style="text-align: start">Item Name</th>
						<th style="text-align: start">Quantity</th>
						<th style="text-align: start">Price</th>
					</tr>
				<% for(Map.Entry<Integer, CartItem> cartItem : (myCart.getCart()).entrySet()){ %>
					<% CartItem currentItem = cartItem.getValue(); %>
					<tr id="item_<%= currentItem.getItemName().replaceAll("\\s+", "_") %>">
	                    <td><%= currentItem.getItemName() %></td>
	                    <td class="item_quantity"><%= currentItem.getPrice() %> * <%= currentItem.getQuantity() %></td> 
	                    <td class="item_price"><%= currentItem.getPrice() * currentItem.getQuantity() %></td>
                	</tr>
				<%} %>
				</table>
				<p id="grand_total">Grand Total:</p>
			</div>
			<div class="choosePayment">
				<label for="payment_method">Select Payment Method:</label>
		        <select id="payment_method">
		            <option value="UPI" selected>UPI</option>
		            <option value="Cash On Delivery">Cash on Delivery</option>
		        </select>
			</div>
			<button class="pay-btn" onclick="handleOrder()">Confirm Order and Pay</button>
		</div>
		</div>
	<%} else { %>
		<div class="empty_cart">
			<i class="fa-solid fa-cart-shopping"></i>
			<p>Empty Cart</p>
			<a href="home.jsp">Start Ordering</a>
		</div>
	<%} %>
<script type="text/javascript" src="counter.js"></script>
</body>
</html>