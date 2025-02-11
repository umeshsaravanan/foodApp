package com.foodApp.DAOInterfaces;

import java.util.List;

import com.foodApp.models.Orders;

public interface OrdersDAO {
	int insert(Orders order);

	List<Orders> getOrdersOfSingleUser(int id);

	void closeOpenedConnections();
}
