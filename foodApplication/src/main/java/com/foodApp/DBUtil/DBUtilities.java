package com.foodApp.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtilities {

	static Connection connection;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
			System.out.println("hi da umesh");
		}
	}

	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection("JDBC:mysql://localhost:3306/foodapp", "root", "root");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void closeConnection(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException("Connection is null");
		}

	}

	public static void closeConnection(Connection connection, Statement stmt) {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException();
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException("Statement is null");
		}

	}

	public static void closeConnection(Connection connection, Statement stmt, PreparedStatement pstmt) {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException();
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException("Statement is null");
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException("Prepared Statement is null");
		}

	}

	public static void closeConnection(Connection connection, PreparedStatement pstmt, ResultSet resultSet) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException("ResultSet is null");
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException("Prepared Statement is null");
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new NullPointerException();
		}

		System.out.println("closed");
	}
}
