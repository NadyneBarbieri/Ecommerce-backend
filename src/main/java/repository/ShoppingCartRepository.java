package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
	  Optional<ShoppingCart> findByUserId(Long userId);
}
