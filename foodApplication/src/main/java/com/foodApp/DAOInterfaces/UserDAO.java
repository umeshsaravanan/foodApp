package com.foodApp.DAOInterfaces;

import java.util.ArrayList;

import com.foodApp.models.User;

public interface UserDAO {

	int insert(User u);

	ArrayList<User> getAll();

	User get(String name);

	int update(int id, String password);

	int delete(int id);

	void closeOpenedConnections();

}
