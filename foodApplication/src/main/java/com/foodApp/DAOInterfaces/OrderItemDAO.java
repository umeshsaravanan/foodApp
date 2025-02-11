package com.foodApp.DAOInterfaces;

import java.util.List;

import com.foodApp.models.OrderItem;

public interface OrderItemDAO {
	int insert(OrderItem orderItem);

	List<OrderItem> getAllOrdersOfSingleOrderId(int id);

	void closeOpenedConnections();
}
