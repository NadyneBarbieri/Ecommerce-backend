package com.nadyne.Akilahyz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nadyne.Akilahyz.model.CartModel;
import com.nadyne.Akilahyz.model.UserModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long> {
    Optional<CartModel> findById(Long id);

	CartModel findByUserId(Long userId);

	CartModel findByUser(UserModel userModel);
}

