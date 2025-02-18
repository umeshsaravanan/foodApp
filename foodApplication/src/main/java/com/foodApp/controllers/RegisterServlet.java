package com.foodApp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodApp.DAOImplementation.UserDAOImpl;
import com.foodApp.models.User;

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
