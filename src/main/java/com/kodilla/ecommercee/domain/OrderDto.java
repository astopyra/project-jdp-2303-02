package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private BigDecimal totalPrice;
    private CartDto cart;

    public OrderDto(Long id, Long cartId) {
        this.id = id;
        this.cart = new CartDto();
    }
}
