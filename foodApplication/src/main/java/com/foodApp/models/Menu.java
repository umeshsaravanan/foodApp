package com.foodApp.models;

public class Menu {
	private int menuId;
	private int restaurantId;
	private String itemName;
	private String description;
	private float price;
	private boolean isAvailable;
	private boolean isVeg;
	private byte[] image;

	public Menu() {
		// Default constructor
	}

	public boolean isVeg() {
		return isVeg;
	}

	public Menu(int restaurantId, String itemName, String description, float price, boolean isAvailable, boolean isVeg,
			byte[] image) {
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.isVeg = isVeg;
		this.image = image;
	}

	public Menu(int menuId, int restaurantId, String itemName, String description, float price, boolean isAvailable,
			boolean isVeg, byte[] image) {
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.isVeg = isVeg;
		this.image = image;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "menuId=" + menuId + ", restaurantId=" + restaurantId + ", itemName='" + itemName + '\''
				+ ", description='" + description + '\'' + ", price=" + price + ", isAvailable=" + isAvailable;
	}
}
