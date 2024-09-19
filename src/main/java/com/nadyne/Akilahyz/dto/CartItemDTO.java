package com.nadyne.Akilahyz.dto;

import java.math.BigDecimal;

public class CartItemDTO {

    private Long id;
    private ProductDTO product;  // Objeto ProductDTO
    private int quantity;
    private BigDecimal totalPrice;

    // Construtor padrão
    public CartItemDTO() {}

    // Construtor com parâmetros
    public CartItemDTO(Long id, ProductDTO product, int quantity, BigDecimal totalPrice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

