package com.foodApp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodApp.DAOImplementation.RestaurantDAOImpl;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RestaurantDAOImpl restaurant = new RestaurantDAOImpl();

		request.setAttribute("restaurants", restaurant.getAll());

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
