package com.foodApp.DAOInterfaces;

import java.util.List;

import com.foodApp.models.CartItem;

public interface CartItemDAO {
	int addToCart(CartItem ci);

	int removeFromCart(int id);

	int updateQuantity(int id, int quantity);

	List<CartItem> getUserCart(int userId);

	void closeOpenedConnections();
}
