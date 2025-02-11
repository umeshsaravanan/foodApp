package com.foodApp.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.DAOInterfaces.OrdersDAO;
import com.foodApp.DBUtil.DBUtilities;
import com.foodApp.models.Orders;

public class OrdersDAOImpl implements OrdersDAO {
	private final String INSERTQUERY = "INSERT INTO orders (user_id, restaurant_id, total_amount, status, payment_mode) VALUES (?, ?, ?, ?, ?)";
	private final String GETBYUSERQUERY = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

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
	public int insert(Orders order) {
		try {
			pstmt = connection.prepareStatement(INSERTQUERY, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, order.getUserId());
			pstmt.setInt(2, order.getRestaurantId());
			pstmt.setFloat(3, order.getTotalAmount());
			pstmt.setString(4, order.getStatus());
			pstmt.setString(5, order.getPaymentMode());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {
				ResultSet rSet = pstmt.getGeneratedKeys();
				if (rSet.next()) {
					return rSet.getInt(1);
				}
			}

			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Orders> getOrdersOfSingleUser(int userId) {
		List<Orders> ordersList = new ArrayList<>();
		try {
			pstmt = connection.prepareStatement(GETBYUSERQUERY);
			pstmt.setInt(1, userId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				ordersList.add(extractFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordersList;
	}

	@Override
	public void closeOpenedConnections() {
		DBUtilities.closeConnection(connection, pstmt, resultSet);
	}

	private Orders extractFromResultSet(ResultSet resultSet) throws SQLException {
		return new Orders(resultSet.getInt("order_id"), resultSet.getInt("user_id"), resultSet.getInt("restaurant_id"),
				resultSet.getTimestamp("order_date"), resultSet.getFloat("total_amount"), resultSet.getString("status"),
				resultSet.getString("payment_mode"));
	}
}
