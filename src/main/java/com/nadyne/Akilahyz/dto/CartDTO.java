package com.nadyne.Akilahyz.dto;

import java.util.List;

public class CartDTO {

    private Long id; // ID do carrinho
    private List<CartItemDTO> items; // Lista de itens do carrinho
    private Double totalPrice; // Preço total do carrinho

    public CartDTO() {}

    public CartDTO(Long id, List<CartItemDTO> items, Double totalPrice) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

