package com.foodApp.controllers;

import java.io.IOException;

import com.foodApp.DAOImplementation.UserDAOImpl;
import com.foodApp.models.User;
import com.foodApp.secure.MyEncrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		UserDAOImpl user = new UserDAOImpl();

		User dbUser = user.get(name);
		if (dbUser != null) {
			if (dbUser.getPassword().equals(MyEncrypt.encrypt(password))) {
				request.getSession().setAttribute("username", name);
				request.getSession().setAttribute("userId", dbUser.getUserId());
				Cookie nameCookie = new Cookie("name", name);
				Cookie idCookie = new Cookie("id", dbUser.getUserId() + "");
				nameCookie.setMaxAge(86400);
				idCookie.setMaxAge(86400);
				response.addCookie(nameCookie);
				response.addCookie(idCookie);
				if ((Boolean) request.getSession().getAttribute("fromConfirmOrder")) {
					request.getSession().setAttribute("fromConfirmOrder", false);
					request.getRequestDispatcher("cartPage.jsp").forward(request, response);
				} else {
					System.out.println("else");
					response.sendRedirect("HomeServlet");
				}
			} else {
				response.sendRedirect("invalid_password.html");
			}
		} else {
			response.sendRedirect("not_a_valid_user.html");
		}

	}
}
