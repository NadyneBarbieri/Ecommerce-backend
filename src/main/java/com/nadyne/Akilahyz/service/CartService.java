package com.nadyne.Akilahyz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadyne.Akilahyz.model.CartModel;
import com.nadyne.Akilahyz.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Optional<CartModel> getCartById(Long id) {
        return cartRepository.findById(id);
    }
}

