package com.nadyne.Akilahyz.dto;

import com.nadyne.Akilahyz.model.CategoryModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

	private Long id;

	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo nome deve ter no mínimo 5 e no máximo 100 caracteres")
	private String name;

	@NotBlank(message = "O atributo descrição é obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo descrição deve ter no mínimo 10 e no máximo 1000 caracteres")
	private String description;

	@Positive(message = "O preço deve ser um valor positivo")
	private Double price;

	private CategoryModel category; // Use Long para o ID

	
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
