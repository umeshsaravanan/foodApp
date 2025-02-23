package com.foodApp.controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodApp.DAOImplementation.UserDAOImpl;
import com.foodApp.models.User;
import com.foodApp.secure.MyEncrypt;

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
				Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
				Cookie idCookie = new Cookie("id", dbUser.getUserId() + "");
				nameCookie.setMaxAge(86400);
				idCookie.setMaxAge(86400);
				response.addCookie(nameCookie);
				response.addCookie(idCookie);
				Object fromConfirmPage = request.getSession().getAttribute("fromConfirmOrder");
				if (fromConfirmPage != null && (Boolean) fromConfirmPage) {
					request.getSession().setAttribute("fromConfirmOrder", false);
					request.getRequestDispatcher("cartPage.jsp").forward(request, response);
				} else {
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
