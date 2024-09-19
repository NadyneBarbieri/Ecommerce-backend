package com.nadyne.Akilahyz.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart")  // Para criar a FK na tabela cart_item_model
    private List<CartItemModel> items;

    @ManyToOne
    @JoinColumn(name = "users")
    private UserModel user;

    private BigDecimal totalPrice;

    // Construtor padrão
    public CartModel() {}

    // Construtor com parâmetros
    public CartModel(List<CartItemModel> items, UserModel user, BigDecimal totalPrice) {
        this.items = items;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemModel> getItems() {
        return items;
    }

    public void setItems(List<CartItemModel> items) {
        this.items = items;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
