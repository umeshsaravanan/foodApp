package com.foodApp.models;

public class Restaurant {
	private int restaurantId;
	private String restaurantName;
	private String location;
	private int deliveryTime;
	private float rating;
	private String restaurantType;
	private String cuisine;
	private byte[] restaurantImage;

	public Restaurant() {

	}

	public Restaurant(String restaurantName, String location, int deliveryTime, float rating, String restaurantType,
			String cuisine, byte[] restaurantImage) {
		this.restaurantName = restaurantName;
		this.location = location;
		this.deliveryTime = deliveryTime;
		this.rating = rating;
		this.restaurantType = restaurantType;
		this.cuisine = cuisine;
		this.restaurantImage = restaurantImage;
	}

	public Restaurant(int restaurantId, String restaurantName, String location, int deliveryTime, float rating,
			String restaurantType, String cuisine, byte[] restaurantImage) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.location = location;
		this.deliveryTime = deliveryTime;
		this.rating = rating;
		this.restaurantType = restaurantType;
		this.cuisine = cuisine;
		this.restaurantImage = restaurantImage;
	}

	public byte[] getRestaurantImage() {
		return restaurantImage;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	@Override
	public String toString() {
		return "restaurantName=" + restaurantName + ", location=" + location + ", deliveryTime=" + deliveryTime
				+ ", rating=" + rating + ", restaurantType=" + restaurantType + ", cuisine=" + cuisine;
	}

}
