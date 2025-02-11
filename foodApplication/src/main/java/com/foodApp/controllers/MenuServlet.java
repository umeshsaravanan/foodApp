package com.foodApp.controllers;

import java.io.IOException;

import com.foodApp.DAOImplementation.MenuDAOImpl;
import com.foodApp.DAOImplementation.RestaurantDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int restaurant_id = Integer.parseInt(request.getParameter("id").trim());

		request.setAttribute("menuItems", new MenuDAOImpl().getAllByRestaurant(restaurant_id));
		request.setAttribute("currentRestaurant", new RestaurantDAOImpl().get(restaurant_id));

		request.getRequestDispatcher("restaurant.jsp").forward(request, response);
	}
}
