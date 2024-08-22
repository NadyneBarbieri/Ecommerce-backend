package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	public List<Category> findAllByDescricaoContainingIgnoreCase (String descricao);
}
