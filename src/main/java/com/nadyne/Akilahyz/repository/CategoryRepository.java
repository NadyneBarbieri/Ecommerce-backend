package com.nadyne.Akilahyz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadyne.Akilahyz.model.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long>{

	public List<CategoryModel> findAllByNameContainingIgnoreCase(String name);
	public Optional<CategoryModel> findByName(String name);

}
