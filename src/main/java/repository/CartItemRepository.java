package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Encontrar todos os itens do carrinho por ID do carrinho
    List<CartItem> findByShoppingCartId(Long shoppingCartId);
    
    // Opcional: encontrar itens do carrinho por ID do produto
    List<CartItem> findByProductId(Long productId);
}
