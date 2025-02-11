package com.foodApp.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foodApp.DAOImplementation.MenuDAOImpl;
import com.foodApp.DAOImplementation.OrderItemDAOImpl;
import com.foodApp.DAOImplementation.OrdersDAOImpl;
import com.foodApp.DAOImplementation.RestaurantDAOImpl;
import com.foodApp.models.Menu;
import com.foodApp.models.OrderItem;
import com.foodApp.models.Orders;
import com.foodApp.models.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object userIdObj = request.getSession().getAttribute("userId");
		int userId = -1;

		if (userIdObj instanceof Integer) {
			userId = (Integer) userIdObj;
		} else if (userIdObj instanceof String) {
			try {
				userId = Integer.parseInt((String) userIdObj);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		response.setContentType("application/json");

		if (userId < 0) {
			request.getSession().setAttribute("fromMyOrders", true);
			response.sendRedirect("login.jsp");
		} else {
			OrdersDAOImpl orders = new OrdersDAOImpl();
			MenuDAOImpl menu = new MenuDAOImpl();

			List<Orders> orderList = orders.getOrdersOfSingleUser(userId);

			if (orderList.size() <= 0) {
				response.sendRedirect("emptyOrders.jsp");
			} else {

				int favtFoodId = findFavtFood(orderList, userId, request);
				int restaurantId = menu.getOne(favtFoodId).getRestaurantId();

				Restaurant restaurant = new RestaurantDAOImpl().get(restaurantId);
				Menu item = menu.getOne(favtFoodId);

				request.getSession().setAttribute("item", item);
				request.getSession().setAttribute("restaurant", restaurant);
				request.getSession().setAttribute("orders", orderList);

				response.sendRedirect("orders.jsp");
			}
		}
	}

	private int findFavtFood(List<Orders> orders, int userId, HttpServletRequest request) {
		int menuId = 0;
		HashMap<Integer, Integer> freq = new HashMap<>();
		OrderItemDAOImpl orderItems = new OrderItemDAOImpl();
		float totalAmount = 0;

		for (Orders order : orders) {
			List<OrderItem> orderList = orderItems.getAllOrdersOfSingleOrderId(order.getOrderId());
			totalAmount += order.getTotalAmount();
			for (OrderItem orderItem : orderList) {
				freq.put(orderItem.getMenuId(), freq.getOrDefault(orderItem.getMenuId(), 0) + 1);
			}
		}

		int maxCount = 0;
		for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
			if (entry.getValue() > maxCount) {
				maxCount = entry.getValue();
				menuId = entry.getKey();
			}
		}

		request.getSession().setAttribute("total", totalAmount);

		return menuId;
	}
}
