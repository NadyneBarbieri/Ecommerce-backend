package com.nadyne.Akilahyz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadyne.Akilahyz.model.CartItemModel;
import com.nadyne.Akilahyz.repository.CartItemRepository;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItemModel> searchByProductName(String productName) {
        return cartItemRepository.findAllByProductNameContainingIgnoreCase(productName);
    }
}

