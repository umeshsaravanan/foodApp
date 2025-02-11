package com.foodApp.DAOInterfaces;

import java.util.List;

import com.foodApp.models.Restaurant;

public interface RestaurantDAO {

	int insert(Restaurant r);

	List<Restaurant> getAll();

	Restaurant get(int id);

	List<Restaurant> get(String cuisine);

	int delete(int id);

	int updateRating(int id, float rating);

	public void closeOpenedConnections();
}
