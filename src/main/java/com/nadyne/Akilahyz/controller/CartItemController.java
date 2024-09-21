package com.nadyne.Akilahyz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadyne.Akilahyz.dto.CartItemDTO;
import com.nadyne.Akilahyz.service.CartItemService;

@RestController
@RequestMapping("/items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    // Método para adicionar um item ao carrinho
    @PostMapping("/add/{productId}")
    public ResponseEntity<CartItemDTO> addCartItem(@PathVariable Long productId, @RequestBody CartItemDTO cartItemDTO) {
        CartItemDTO createdCartItem = cartItemService.addCartItem(productId, cartItemDTO);
        return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
    }


    // Método para remover um item do carrinho
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long id) {
        cartItemService.removeCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para atualizar a quantidade de um item no carrinho
    @PutMapping("/update/{id}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long id, @RequestBody CartItemDTO cartItemDTO) {
        CartItemDTO updatedCartItem = cartItemService.updateCartItem(id, cartItemDTO);
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

    // Método para listar todos os itens de um carrinho específico
    @GetMapping("/list/{cartId}")
    public ResponseEntity<List<CartItemDTO>> listCartItems(@PathVariable Long cartId) {
        List<CartItemDTO> cartItems = cartItemService.listCartItems(cartId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
}

