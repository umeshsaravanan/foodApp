package com.foodApp.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.DAOInterfaces.OrderItemDAO;
import com.foodApp.DBUtil.DBUtilities;
import com.foodApp.models.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {
	private final String INSERTQUERY = "INSERT INTO order_items (order_id, menu_id, quantity, total) VALUES (?, ?, ?, ?)";
	private final String GETALLQUERY = "SELECT * FROM order_items WHERE order_id = ?";

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
	public int insert(OrderItem orderItem) {
		try {
			pstmt = connection.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getMenuId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setFloat(4, orderItem.getTotal());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<OrderItem> getAllOrdersOfSingleOrderId(int orderId) {
		List<OrderItem> orderItemList = new ArrayList<>();
		try {
			pstmt = connection.prepareStatement(GETALLQUERY);
			pstmt.setInt(1, orderId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				orderItemList.add(extractFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItemList;
	}

	@Override
	public void closeOpenedConnections() {
		DBUtilities.closeConnection(connection, pstmt, resultSet);
	}

	private OrderItem extractFromResultSet(ResultSet resultSet) throws SQLException {
		return new OrderItem(resultSet.getInt("order_item_id"), resultSet.getInt("order_id"),
				resultSet.getInt("menu_id"), resultSet.getInt("quantity"), resultSet.getFloat("total"));
	}
}
