<%@page import="com.foodApp.models.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
	.cart_card {
	    height: 4rem;
	    width: 70%;
	    margin: 0 auto;
	    border-radius: 8px;
	    background-color: green;
	    position: fixed; 
	    bottom: 2rem; 
	    left: 50%; 
	    transform: translateX(-50%); 
	    z-index: 10; 
	    padding: 0px 3rem;
	    display: flex;
	    align-items: center;
	    justify-content: space-between;
	    color: white;
	    font-family: monospace;
	}
	
	.hide {
		display: none;
	}
	
	.cart_card p a{
		text-decoration: none;
		color: white;
		font-size: 1.025rem;
	}
	
	.cart_card p a:hover{
		text-decoration: underline;
	}
	
	@media (max-width: 480px) {
		.cart_card {
			width: 80%;
		}
		
		.cart_card p a{
			font-size: small;
		}
	}

</style>
</head>
<body>
	<div class="cart_card <%= (session.getAttribute("cart") == null || ((Cart)session.getAttribute("cart")).getSize() == 0) ? "hide" : "" %>" id="cart_card">
	    <h3>
	        <% if (session.getAttribute("cart") != null) { %>
	            Added <%= ((Cart)session.getAttribute("cart")).getSize() %> Items in Cart
	        <% } else { %>
	            Your Cart is Empty
	        <% } %>
	    </h3>
	    <p><a href="cartPage.jsp">View Cart</a></p>
	</div>
</body>
</html>