package com.nadyne.Akilahyz.dto;

import com.nadyne.Akilahyz.model.CategoryModel;

public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private CategoryModel category; 
	private String photo;

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, String description, Double price, String photo, CategoryModel category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.photo = photo;
		this.category = category;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel categoryModel) {
		this.category = categoryModel;
	}

	public String getPhoto() {
		return photo;
	}

}
