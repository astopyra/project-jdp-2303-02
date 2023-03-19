package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Getter
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    public Order() {
    }

    public Order(Long id, Long cartId) {
    }


    public Long getId() {

        return id;
    }

    public Order(Cart cart) {

        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
