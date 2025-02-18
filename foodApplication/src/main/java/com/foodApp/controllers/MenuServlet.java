package com.foodApp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodApp.DAOImplementation.MenuDAOImpl;
import com.foodApp.DAOImplementation.RestaurantDAOImpl;

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
