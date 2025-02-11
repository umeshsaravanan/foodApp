package com.foodApp.models;

public class User {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private String address;

	public User() {

	}

	public User(String userName, String password, String email, String address) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
	}

	public User(Integer userId, String userName, String password, String email, String address) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", address=" + address;
	}

}
