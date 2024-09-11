package com.nadyne.Akilahyz.dto;

public class CartItemDTO {

    private Long productId; // ID do produto
    private String productName; // Nome do produto
    private Integer quantity; // Quantidade do produto
    private Double price; // Preço unitário do produto
    private Double totalPrice; // Preço total do item (quantidade * preço)

    public CartItemDTO() {}

    public CartItemDTO(Long productId, String productName, Integer quantity, Double price, Double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    // Getters e Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.totalPrice = this.price * quantity; // Atualiza o preço total quando a quantidade muda
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

