package com.nadyne.Akilahyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nadyne.Akilahyz.model.CartItemModel;
import com.nadyne.Akilahyz.model.CartModel;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {

    /**
     * Busca todos os itens do carrinho pelo ID do produto.
     * 
     * @param productId ID do produto
     * @return Lista de itens do carrinho associados ao produto
     */
    List<CartItemModel> findAllByProduct_Id(Long productId);

    /**
     * Busca todos os itens do carrinho associado a um CartModel específico.
     * 
     * @param cart O modelo do carrinho
     * @return Lista de itens do carrinho associados
     */
    List<CartItemModel> findAllByCart(CartModel cart);
}
