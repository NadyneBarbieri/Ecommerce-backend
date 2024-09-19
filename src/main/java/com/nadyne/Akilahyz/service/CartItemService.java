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
   //busca produtos por id
    public List<CartItemModel> findItemsByProduct(Long product) {
        return cartItemRepository.findAllByProduct_Id(product);
    }
}

