package com.foodApp.models;

public class OrderItem {
	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private float total;

	public OrderItem() {
	}

	public OrderItem(int orderId, int menuId, int quantity, float total) {
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.total = total;
	}

	public OrderItem(int orderItemId, int orderId, int menuId, int quantity, float total) {
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.total = total;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId + ", quantity=" + quantity
				+ ", total=" + total;
	}
}
