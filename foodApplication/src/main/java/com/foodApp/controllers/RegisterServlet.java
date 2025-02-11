package com.foodApp.controllers;

import java.io.IOException;

import com.foodApp.DAOImplementation.UserDAOImpl;
import com.foodApp.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String retypePassword = request.getParameter("retypePassword");

		if (password.equals(retypePassword)) {
			new UserDAOImpl().insert(new User(name, password, email, ""));
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("password_mismatch.html");
		}
	}
}
