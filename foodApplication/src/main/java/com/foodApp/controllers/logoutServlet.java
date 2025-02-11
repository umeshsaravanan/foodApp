package com.foodApp.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for (Cookie c : request.getCookies()) {
			if (c.getName().equals("name")) {
				c.setMaxAge(0);
				request.getSession().removeAttribute("username");
				response.addCookie(c);
			}
			if (c.getName().equals("id")) {
				c.setMaxAge(0);
				request.getSession().removeAttribute("id");
				response.addCookie(c);
			}
		}
		response.sendRedirect("login.jsp");
	}

}
