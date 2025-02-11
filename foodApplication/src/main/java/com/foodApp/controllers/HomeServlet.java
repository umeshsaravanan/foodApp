package com.foodApp.controllers;

import java.io.IOException;

import com.foodApp.DAOImplementation.RestaurantDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
