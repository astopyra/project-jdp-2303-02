package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private BigDecimal totalPrice;
    private CartDto cart;
}
