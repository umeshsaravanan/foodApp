package com.foodApp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
