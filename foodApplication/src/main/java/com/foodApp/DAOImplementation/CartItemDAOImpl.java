package com.foodApp.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.DAOInterfaces.CartItemDAO;
import com.foodApp.DBUtil.DBUtilities;
import com.foodApp.models.CartItem;

public class CartItemDAOImpl implements CartItemDAO {
	private final String ADDQUERY = "INSERT INTO cart_item (menu_id, restaurant_id, item_name, price, quantity) VALUES (?, ?, ?, ?, ?, ?);";
	private final String DELETEQUERY = "DELETE FROM cart_item WHERE cart_item_id = ?;";
	private final String UPDATEQUERY = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?;";
	private final String GETCARTQUERY = "SELECT * FROM cart_item WHERE user_id = ?;";

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
	public int addToCart(CartItem ci) {
		try {
			pstmt = connection.prepareStatement(ADDQUERY);
			pstmt.setInt(1, ci.getMenuId());
			pstmt.setInt(2, ci.getRestaurantId());
			pstmt.setString(3, ci.getItemName());
			pstmt.setFloat(4, ci.getPrice());
			pstmt.setInt(5, ci.getQuantity());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int removeFromCart(int id) {
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
	public int updateQuantity(int id, int quantity) {
		try {
			pstmt = connection.prepareStatement(UPDATEQUERY);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<CartItem> getUserCart(int userId) {
		List<CartItem> cartItems = new ArrayList<>();
		try {
			pstmt = connection.prepareStatement(GETCARTQUERY);
			pstmt.setInt(1, userId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				cartItems.add(extractFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartItems;
	}

	@Override
	public void closeOpenedConnections() {
		DBUtilities.closeConnection(connection, pstmt, resultSet);
	}

	private CartItem extractFromResultSet(ResultSet resultSet) throws SQLException {
		return new CartItem(resultSet.getInt("cart_item_id"), resultSet.getInt("menu_id"),
				resultSet.getInt("restaurant_id"), resultSet.getString("item_name"), resultSet.getFloat("price"),
				resultSet.getInt("quantity"));
	}
}
