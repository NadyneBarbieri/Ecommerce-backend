package com.nadyne.Akilahyz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "produtos")
public class ProductModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo Nome é Obrigatório!")
    private String name;

    @NotBlank(message = "O atributo Descrição é Obrigatório!")
    private String description;

    @NotNull(message = "O atributo preço é Obrigatório!")
    private Double price;

  
    private String photo;

    @ManyToOne
    @JoinColumn(name = "categoria", nullable = false)  // Assegura que a coluna não pode ser nula
    private CategoryModel category;

    public ProductModel() {}

    public ProductModel(Long id, String name, String description, Double price, String photo, CategoryModel category) {
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

    public String getDescription() { // Corrigido para 'getDescription'
        return description;
    }

    public void setDescription(String description) { // Corrigido para 'setDescription'
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhoto() { // Corrigido para 'getPhoto'
        return photo;
    }

    public void setPhoto(String photo) { // Corrigido para 'setPhoto'
        this.photo = photo;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }
}
