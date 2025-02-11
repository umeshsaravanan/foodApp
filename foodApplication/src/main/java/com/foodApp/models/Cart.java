package com.foodApp.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private HashMap<Integer, CartItem> myCart;

	public Cart() {
		myCart = new HashMap<Integer, CartItem>();
	}

	public void addToCart(CartItem item) {
		if (myCart.containsKey(item.getMenuId())) {
			myCart.get(item.getMenuId()).setQuantity(myCart.get(item.getMenuId()).getQuantity() + item.getQuantity());
		} else {
			myCart.put(item.getMenuId(), item);
		}
	}

	public void updateItem(int menuId, int quantity) {
		if (myCart.containsKey(menuId)) {
			if (quantity <= 0) {
				removeItem(menuId);
			} else {
				myCart.get(menuId).setQuantity(quantity);
			}
		}
	}

	public void removeItem(int menuId) {
		myCart.remove(menuId);
	}

	public void clear() {
		myCart.clear();
	}

	public Map<Integer, CartItem> getCart() {
		return myCart;
	}

	@Override
	public String toString() {
		return "Cart [myCart=" + myCart + "]";
	}

	public int getSize() {
		if (myCart == null)
			return 0;
		return myCart.size();
	}
}
