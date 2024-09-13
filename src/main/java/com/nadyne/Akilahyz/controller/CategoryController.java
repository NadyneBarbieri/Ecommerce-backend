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

import com.nadyne.Akilahyz.dto.CategoryDTO;
import com.nadyne.Akilahyz.model.CategoryModel;
import com.nadyne.Akilahyz.model.ProductModel;
import com.nadyne.Akilahyz.repository.CategoryRepository;
import com.nadyne.Akilahyz.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;


	public CategoryModel getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
	}

	// Converter Model para DTO
	private CategoryDTO convertToDto(CategoryModel categoryModel) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(categoryModel.getId());
		dto.setName(categoryModel.getName());
		return dto;
	}

	// Converter DTO para Model
	private CategoryModel convertToModel(CategoryDTO dto) {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setId(dto.getId());
		categoryModel.setName(dto.getName());
		return categoryModel;
	}

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAll() {
		List<CategoryModel> categories = categoryRepository.findAll();
		List<CategoryDTO> dtos = categories.stream().map(this::convertToDto).toList();
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
		return categoryRepository.findById(id).map(categoryModel -> ResponseEntity.ok(convertToDto(categoryModel)))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<CategoryDTO>> getByName(@PathVariable String category) {
		List<CategoryModel> categories = categoryRepository.findAllByNameContainingIgnoreCase(category);
		List<CategoryDTO> dtos = categories.stream().map(this::convertToDto).toList();
		return ResponseEntity.ok(dtos);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> post(@Valid @RequestBody CategoryDTO dto) {
		CategoryModel categoryModel = convertToModel(dto);
		categoryModel = categoryRepository.save(categoryModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(categoryModel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> put(@PathVariable Long id, @Valid @RequestBody CategoryDTO dto) {
		if (!categoryRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		CategoryModel categoryModel = convertToModel(dto);
		categoryModel.setId(id);
		categoryModel = categoryRepository.save(categoryModel);
		return ResponseEntity.ok(convertToDto(categoryModel));
	}

//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable Long id) {
//	    categoryRepository.deleteById(id);
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    // Verifica se existem produtos associados à categoria
	    List<ProductModel> products = productRepository.findByCategoryId(id);

	    if (!products.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body("Não é possível excluir a categoria, pois existem produtos associados.");
	    }

	    // Se não houver produtos associados, a categoria pode ser excluída
	    categoryRepository.deleteById(id);
	    return ResponseEntity.status(HttpStatus.OK).body("Categoria excluída com sucesso.");
	}

	

}
