package com.nadyne.Akilahyz.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O atributo Nome é Obrigatório!")
	private String name;

	@Schema(example = "email@email.com")
	@NotNull(message = "O atributo email é obrigatório!")
	@Email(message = "O atributo deve ser um email válido!")
	private String email;

	@NotBlank(message = "O atributo senha é obrigatório!")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	private String password;

	private String photo;

	private String type;


	public UserModel() {
	}

	public UserModel(Long id, String name, String email, String password, String photo, String type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.photo = photo;
		this.type = type;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
