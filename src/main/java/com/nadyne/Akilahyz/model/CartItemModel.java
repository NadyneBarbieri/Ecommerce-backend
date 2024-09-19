package com.nadyne.Akilahyz.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class CartItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "products")
    private ProductModel product;
    private int quantity;
    private BigDecimal totalPrice;

    // Construtor padrão
    public CartItemModel() {}

    // Construtor com parâmetros
    public CartItemModel(ProductModel product, int quantity, BigDecimal totalPrice) {
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

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
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
