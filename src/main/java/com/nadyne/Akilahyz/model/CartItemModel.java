package com.nadyne.Akilahyz.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class CartItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Associa um item de carrinho a um produto
    @JoinColumn(name = "product") // Nome da coluna no banco de dados
    private ProductModel product; // Alterado para ProductModel
    
    @ManyToOne
    @JoinColumn(name = "cart") // ou o nome da coluna correspondente
    private CartModel cart;

    private int quantity;
    private BigDecimal totalPrice;

    // Construtor padrão
    public CartItemModel() {}

    // Construtor com parâmetros
    public CartItemModel(ProductModel product, int quantity, BigDecimal totalPrice) {
        this.product = product; // Alterado para ProductModel
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

    public ProductModel getProduct() { // Alterado para retornar ProductModel
        return product;
    }

    public void setProduct(ProductModel product) { // Alterado para aceitar ProductModel
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
