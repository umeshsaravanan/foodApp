package com.foodApp.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.DAOInterfaces.MenuDAO;
import com.foodApp.DBUtil.DBUtilities;
import com.foodApp.models.Menu;

public class MenuDAOImpl implements MenuDAO {
	private final String INSERTQUERY = "INSERT INTO menu (restaurant_id, item_name, description, price, is_available, image) VALUES (?, ?, ?, ?, ?, ?)";
	private final String GETALLQUERY = "SELECT * FROM menu";
	private final String GETBYRESTAURANTQUERY = "SELECT * FROM menu WHERE restaurant_id = ?";
	private final String GETONEQUERY = "SELECT * FROM menu WHERE menu_id = ?";
	private final String UPDATEPRICEQUERY = "UPDATE menu SET price = ? WHERE menu_id = ?";
	private final String UPDATEAVAILABILITYQUERY = "UPDATE menu SET is_available = ? WHERE menu_id = ?";
	private final String DELETEQUERY = "DELETE FROM menu WHERE menu_id = ?";

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
	public int insert(Menu menu) {
		try {
			pstmt = connection.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setFloat(4, menu.getPrice());
			pstmt.setBoolean(5, menu.isAvailable());
			pstmt.setBytes(6, menu.getImage());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Menu> getAll() {
		List<Menu> menuList = new ArrayList<>();
		try {
			pstmt = connection.prepareStatement(GETALLQUERY);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				menuList.add(extractFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public List<Menu> getAllByRestaurant(int restaurantId) {
		List<Menu> menuList = new ArrayList<>();
		try {
			pstmt = connection.prepareStatement(GETBYRESTAURANTQUERY);
			pstmt.setInt(1, restaurantId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				menuList.add(extractFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public Menu getOne(int menuId) {
		Menu menu = null;
		try {
			pstmt = connection.prepareStatement(GETONEQUERY);
			pstmt.setInt(1, menuId);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				menu = extractFromResultSet(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public int updatePrice(int menuId, float price) {
		try {
			pstmt = connection.prepareStatement(UPDATEPRICEQUERY);
			pstmt.setFloat(1, price);
			pstmt.setInt(2, menuId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateAvailability(int menuId, int isAvailable) {
		try {
			pstmt = connection.prepareStatement(UPDATEAVAILABILITYQUERY);
			pstmt.setInt(1, isAvailable);
			pstmt.setInt(2, menuId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int menuId) {
		try {
			pstmt = connection.prepareStatement(DELETEQUERY);
			pstmt.setInt(1, menuId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void closeOpenedConnections() {
		DBUtilities.closeConnection(connection, pstmt, resultSet);
	}

	private Menu extractFromResultSet(ResultSet resultSet) throws SQLException {
		return new Menu(resultSet.getInt("menu_id"), resultSet.getInt("restaurant_id"),
				resultSet.getString("item_name"), resultSet.getString("description"), resultSet.getFloat("price"),
				resultSet.getBoolean("is_available"), resultSet.getBoolean("is_veg"), resultSet.getBytes("image"));
	}
}
