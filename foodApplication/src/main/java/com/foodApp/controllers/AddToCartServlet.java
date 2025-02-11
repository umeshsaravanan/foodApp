package com.foodApp.controllers;

import java.io.IOException;

import com.foodApp.DAOImplementation.MenuDAOImpl;
import com.foodApp.models.Cart;
import com.foodApp.models.CartItem;
import com.foodApp.models.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static Cart cart;

	// Static initialization block for the cart object
	static {
		cart = new Cart();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Retrieve action and menuId from the request
			String action = request.getParameter("action");
			int menuId = Integer.parseInt(request.getParameter("id"));

			if ("add".equals(action)) {
				// Add item to cart
				MenuDAOImpl menuDAO = new MenuDAOImpl();
				Menu currentMenuItem = menuDAO.getOne(menuId);
				cart.addToCart(new CartItem(currentMenuItem.getMenuId(), currentMenuItem.getRestaurantId(),
						currentMenuItem.getItemName(), currentMenuItem.getPrice(), 1 // Default quantity
				));

				// Update session with the cart object
				request.getSession().setAttribute("cart", cart);

				// Send response
				response.getWriter().println(cart.getSize());

			} else if ("update".equals(action)) {
				// Update item quantity in the cart
				int numValue = Integer.parseInt(request.getParameter("num"));
				cart.updateItem(menuId, numValue);

				// Update session with the cart object
				request.getSession().setAttribute("cart", cart);

				// Send updated cart size to the client
				response.getWriter().println(cart.getSize());

			} else if ("remove".equals(action)) {
				// Handle invalid action
				cart.removeItem(menuId);
				response.getWriter().println(cart.getSize());
			} else if ("clear".equals(action)) {
				cart.clear();
				response.getWriter().println(cart.getSize());
			}

		} catch (NumberFormatException e) {
			// Handle invalid number format
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("Invalid input: " + e.getMessage());

		} catch (Exception e) {
			// Handle generic errors
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("An error occurred: " + e.getMessage());
		}
	}
}
