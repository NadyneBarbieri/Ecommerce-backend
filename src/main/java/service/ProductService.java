package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ProductDTO;
import model.Product;
import repository.ProductRepository;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    
    @Autowired
    private CategoryService categoryService;

    public ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(categoryService.toDTO(product.getCategory())); // Converte a Category para CategoryDTO
        return dto;
    }

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCategory(categoryService.toEntity(productDTO.getCategory())); // Converte a CategoryDTO para Category
        return product;
    }

	    public Product cadastrarProduct(ProductDTO productDTO) {
	        Product product = toEntity(productDTO);
	        return repository.save(product);
	    }

	    public Product atualizarProduct(ProductDTO productDTO) {
	        Product product = toEntity(productDTO);
	        return repository.save(product);
	    }
}
