package com.foodApp.DAOInterfaces;

import java.util.List;

import com.foodApp.models.Menu;

public interface MenuDAO {
	int insert(Menu m);

	List<Menu> getAll();

	List<Menu> getAllByRestaurant(int id);

	Menu getOne(int id);

	int updatePrice(int id, float price);

	int updateAvailability(int id, int isAvailable);

	int delete(int id);

	void closeOpenedConnections();
}
