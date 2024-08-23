package controller;

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

import model.Product;
import repository.ProductRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public ResponseEntity <List<Product>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/porId/{id}")
	public ResponseEntity<Product> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());				
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Product>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));				
	}
	
	@PostMapping
	public ResponseEntity<Product> Post(@RequestBody Product produto){
	    Product novoProduto = repository.save(produto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
	}

	@PutMapping
	public ResponseEntity<Product> Put(@RequestBody Product produto){
	    Product produtoAtualizado = repository.save(produto);
	    return ResponseEntity.status(HttpStatus.OK).body(produtoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public void Delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
