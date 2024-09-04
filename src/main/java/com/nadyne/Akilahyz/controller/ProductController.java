package com.nadyne.Akilahyz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadyne.Akilahyz.dto.ProductDTO;
import com.nadyne.Akilahyz.model.ProductModel;
import com.nadyne.Akilahyz.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	private ProductDTO convertToDto(ProductModel productModel) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productModel.getId());
		productDTO.setName(productModel.getName());
		productDTO.setDescription(productModel.getDescription());
		productDTO.setPrice(productModel.getPrice());
		productDTO.setCategory(productModel.getCategory()); // Ajuste conforme necessário
		return productDTO;
	}

	private ProductModel convertToModel(ProductDTO productDTO) {
		ProductModel productModel = new ProductModel();
		productModel.setId(productDTO.getId());
		productModel.setName(productDTO.getName());
		productModel.setDescription(productDTO.getDescription());
		productModel.setPrice(productDTO.getPrice());
		productModel.setCategory(productDTO.getCategory()); // Ajuste conforme necessário
		return productModel;
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAll() {
		List<ProductModel> products = productRepository.findAll();
		List<ProductDTO> dtos = products.stream().map(this::convertToDto).toList();
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
		return productRepository.findById(id).map(productModel -> ResponseEntity.ok(convertToDto(productModel)))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{name}")
	public ResponseEntity<List<ProductDTO>> getByName(@PathVariable String name) {
		List<ProductModel> products = productRepository.findAllByNameContainingIgnoreCase(name);
		List<ProductDTO> dtos = products.stream().map(this::convertToDto).toList();
		return ResponseEntity.ok(dtos);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> post(@Valid @RequestBody ProductDTO productDTO) {
		// Converte o DTO para o modelo
		ProductModel productModel = convertToModel(productDTO);
		// Salva o modelo no repositório
		ProductModel savedProduct = productRepository.save(productModel);
		// Converte o modelo salvo de volta para o DTO
		ProductDTO savedProductDTO = convertToDto(savedProduct);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> put(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
		if (!productRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		ProductModel productModel = convertToModel(dto);
		productModel.setId(id);
		productModel = productRepository.save(productModel);
		return ResponseEntity.ok(convertToDto(productModel));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productRepository.deleteById(id);
	}
}
