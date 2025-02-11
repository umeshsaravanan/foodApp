package com.foodApp.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.foodApp.DAOInterfaces.UserDAO;
import com.foodApp.DBUtil.DBUtilities;
import com.foodApp.models.User;
import com.foodApp.secure.MyEncrypt;

public class UserDAOImpl implements UserDAO {
	private static Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private static ArrayList<User> studentList = new ArrayList<User>();

	private static String INSERTQUERY = "INSERT INTO USERS (user_name,password,email,address) VALUES(?,?,?,?)";
	private static String GETALLQUERY = "SELECT * FROM USERS";
	private static String GETSPECIFICQUERY = "SELECT * FROM USERS WHERE USER_NAME = ?";
	private static String UPDATEQUERY = "UPDATE USERS SET PASSWORD = ? WHERE USER_ID = ?";
	private static String DELETEQUERY = "DELETE FROM USERS WHERE USER_ID = ?";

	static {
		connection = DBUtilities.getConnection();
	}

	@Override
	public int insert(User s) {
		try {
			pstmt = connection.prepareStatement(INSERTQUERY);
			pstmt.setString(1, s.getUserName());
			pstmt.setString(2, MyEncrypt.encrypt(s.getPassword()));
			pstmt.setString(3, s.getEmail());
			pstmt.setString(4, s.getAddress());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<User> getAll() {
		try {
			pstmt = connection.prepareStatement(GETALLQUERY);

			resultSet = pstmt.executeQuery();

			studentList = extractFromResultSet(resultSet);
			if (!studentList.isEmpty())
				return studentList;
			else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public User get(String name) {
		try {
			pstmt = connection.prepareStatement(GETSPECIFICQUERY);
			pstmt.setString(1, name);
			resultSet = pstmt.executeQuery();

			studentList = extractFromResultSet(resultSet);
			if (!studentList.isEmpty())
				return studentList.get(0);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int update(int id, String password) {
		try {
			pstmt = connection.prepareStatement(UPDATEQUERY);
			pstmt.setString(1, password);
			pstmt.setInt(2, id);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int id) {
		try {
			pstmt = connection.prepareStatement(DELETEQUERY);
			pstmt.setInt(1, id);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private ArrayList<User> extractFromResultSet(ResultSet resultSet) {

		try {
			studentList.clear();
			while (resultSet.next()) {
				studentList.add(new User(resultSet.getInt("user_id"), resultSet.getString("user_name"),
						resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("address")));
			}
			return studentList;
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
