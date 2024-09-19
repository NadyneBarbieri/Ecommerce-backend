package com.nadyne.Akilahyz.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO {

    private Long id;
    private UserDTO user;  // Objeto UserDTO
    private List<CartItemDTO> items;  // Lista de itens no carrinho
    private BigDecimal totalPrice;

    // Construtor padrão
    public CartDTO() {}

    // Construtor com parâmetros
    public CartDTO(Long id, UserDTO user, List<CartItemDTO> items, BigDecimal totalPrice) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

