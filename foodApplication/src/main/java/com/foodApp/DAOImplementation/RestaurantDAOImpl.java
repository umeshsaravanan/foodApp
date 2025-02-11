package com.foodApp.DAOImplementation;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.DAOInterfaces.RestaurantDAO;
import com.foodApp.DBUtil.DBUtilities;
import com.foodApp.models.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {
	private final String INSERTQUERY = "INSERT INTO restaurant (restaurant_name,location,delivery_time,rating,type,cuisine) values (?,?,?,?,?,?)";
	private final String GETALLQUERY = "SELECT * FROM restaurant";
	private final String GETBYIDQUERY = "SELECT * FROM restaurant WHERE restaurant_id = ?";
	private final String GETBYCUISINEQUERY = "SELECT * FROM restaurant WHERE cuisine = ?";
	private final String DELETEQUERY = "DELETE FROM restaurant WHERE restaurant_id = ?";
	private final String UPDATERATINGQUERY = "UPDATE restaurant SET rating = ? WHERE restaurant_id = ?";

	private static ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	private static Connection connection;
	private static PreparedStatement pstmt;
	private static ResultSet resultSet;

	static {
		try {
			connection = DBUtilities.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Restaurant r) {
		try {
			pstmt = connection.prepareStatement(INSERTQUERY);
			pstmt.setString(1, r.getRestaurantName());
			pstmt.setString(2, r.getLocation());
			pstmt.setInt(3, r.getDeliveryTime());
			pstmt.setFloat(4, r.getRating());
			pstmt.setString(5, r.getRestaurantType());
			pstmt.setString(6, r.getCuisine());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Restaurant> getAll() {

		try {
			pstmt = connection.prepareStatement(GETALLQUERY);
			resultSet = pstmt.executeQuery();

			extractFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantList;
	}

	@Override
	public Restaurant get(int id) {
		Restaurant restaurant = null;
		try {
			pstmt = connection.prepareStatement(GETBYIDQUERY);
			pstmt.setInt(1, id);

			resultSet = pstmt.executeQuery();

			restaurant = extractFromResultSet(resultSet).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}

	@Override
	public List<Restaurant> get(String cuisine) {
		try {
			pstmt = connection.prepareStatement(GETBYCUISINEQUERY);
			pstmt.setString(1, cuisine);

			resultSet = pstmt.executeQuery();

			extractFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!restaurantList.isEmpty())
			return restaurantList;

		return null;
	}

	@Override
	public int delete(int id) {
		try {
			pstmt = connection.prepareStatement(DELETEQUERY);
			pstmt.setInt(1, id);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateRating(int id, float rating) {
		try {
			pstmt = connection.prepareStatement(UPDATERATINGQUERY);
			pstmt.setFloat(1, rating);
			pstmt.setInt(2, id);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	private ArrayList<Restaurant> extractFromResultSet(ResultSet resultSet) {

		try {
			restaurantList.clear();
			while (resultSet.next()) {
				Blob imageBlob = resultSet.getBlob("restaurant_image");
				byte[] imageData = imageBlob != null ? imageBlob.getBytes(1, (int) imageBlob.length()) : null;
				restaurantList.add(new Restaurant(resultSet.getInt("restaurant_id"),
						resultSet.getString("restaurant_name"), resultSet.getString("location"),
						resultSet.getInt("delivery_time"), resultSet.getFloat("rating"), resultSet.getString("type"),
						resultSet.getString("cuisine"), imageData));
			}
			return restaurantList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void closeOpenedConnections() {
		DBUtilities.closeConnection(connection, pstmt, resultSet);
	}
}
