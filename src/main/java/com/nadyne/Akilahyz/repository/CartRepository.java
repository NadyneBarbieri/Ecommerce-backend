package com.nadyne.Akilahyz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nadyne.Akilahyz.model.CartModel;
import com.nadyne.Akilahyz.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.nadyne.Akilahyz.model.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long> {

	// Método para buscar o carrinho pelo ID do usuário
	Optional<CartModel> findByUserId(Long user);
}


