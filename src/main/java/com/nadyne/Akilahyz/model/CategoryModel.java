package com.nadyne.Akilahyz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class CategoryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo Descrição é obrigatório e não pode conter espaços em branco")
	private String name;

//	@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
//	@JsonIgnoreProperties("tema")
//	private List<ProdutoModel> postagem;

	public CategoryModel() {}
	
	public CategoryModel(Long id, String name) {
		this.id = id;
		this.name = name;
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

}
