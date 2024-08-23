package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.CategoryDTO;
import model.Category;
import repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    // Converte de Category para CategoryDTO
    public CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    // Converte de CategoryDTO para Category
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

    // Cadastra uma nova categoria
    public Category cadastrarCategory(CategoryDTO categoryDTO) {
        Category category = toEntity(categoryDTO);
        return repository.save(category);
    }

    // Atualiza uma categoria existente
    public Category atualizarCategory(CategoryDTO categoryDTO) {
        Category category = toEntity(categoryDTO);
        return repository.save(category);
    }
}
