package com.foodApp.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodApp.DAOImplementation.OrderItemDAOImpl;
import com.foodApp.DAOImplementation.OrdersDAOImpl;
import com.foodApp.models.Cart;
import com.foodApp.models.CartItem;
import com.foodApp.models.OrderItem;
import com.foodApp.models.Orders;

@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paymentMethod = request.getParameter("paymentMethod");

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		OrdersDAOImpl orders = new OrdersDAOImpl();
		int userId = extractIdFromCookies(request.getCookies());

		response.setContentType("application/json");
		if (userId == -1) {
			request.getSession().setAttribute("fromConfirmOrder", true);
			response.sendRedirect("login.jsp");
		} else {

			int restaurantId = 0;
			float totalAmount = 0;

			for (Map.Entry<Integer, CartItem> cEntry : cart.getCart().entrySet()) {
				restaurantId = cEntry.getValue().getRestaurantId();

				totalAmount += cEntry.getValue().getPrice() * cEntry.getValue().getQuantity();
			}

			int orderId = orders.insert(new Orders(userId, restaurantId, totalAmount, "order placed", paymentMethod));
			insertIntoOrderItem(cart.getCart(), orderId);

			request.getSession().removeAttribute("cart");

			response.getWriter().write("order confirmed");
		}
	}

	private int extractIdFromCookies(Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("id"))
				return Integer.parseInt(cookie.getValue());
		}

		return -1;
	}

	private void insertIntoOrderItem(Map<Integer, CartItem> map, int orderId) {
		for (Map.Entry<Integer, CartItem> cEntry : map.entrySet()) {
			OrderItemDAOImpl orderItem = new OrderItemDAOImpl();
			orderItem.insert(new OrderItem(orderId, cEntry.getValue().getMenuId(), cEntry.getValue().getQuantity(),
					cEntry.getValue().getQuantity() * cEntry.getValue().getPrice()));
		}
	}
}
