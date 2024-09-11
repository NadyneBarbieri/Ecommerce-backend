package com.nadyne.Akilahyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nadyne.Akilahyz.model.CartItemModel;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {
    // Método customizado para buscar por nome de produto, ignorando maiúsculas/minúsculas
    List<CartItemModel> findAllByProductNameContainingIgnoreCase(String productName);
}
