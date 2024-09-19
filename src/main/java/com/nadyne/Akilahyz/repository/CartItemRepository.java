package com.nadyne.Akilahyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nadyne.Akilahyz.model.CartItemModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.nadyne.Akilahyz.model.CartItemModel;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {

    // Método customizado para buscar itens do carrinho pelo ID do produto
    List<CartItemModel> findAllByProduct_Id(Long productId);
}

