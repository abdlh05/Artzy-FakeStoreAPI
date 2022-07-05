package com.blogspot.codesgram.merkletest.Models;

public class ProductModel {
	public int id;
	public String title;
	public double price;
	public String description;
	public String category;
	public String image;

	public ProductModel(int id, String title, double price, String description, String category, String image) {
		//initialized parameter to component
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
	}

	//get data model
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public String getImage() {
		return image;
	}
}
