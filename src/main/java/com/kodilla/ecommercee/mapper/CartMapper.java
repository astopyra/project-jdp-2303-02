package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {
    public Cart mapCartDtoToCart(CartDto cartDto) {
        return new Cart(
                cartDto.getCartId(),
                new User(cartDto.getUserId(), null, null, false, null),
                new ArrayList<>()
        );
    }

    public ProductDto mapProductToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getProductName(), product.getProductPrice());
    }

    public List<ProductDto> mapProductsToDtoList(List<Product> products) {
        return products.stream()
                .map(this::mapProductToProductDto)
                .collect(Collectors.toList());
    }

    public CartDto mapCartToCartDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getProducts().stream().map(this::mapProductToProductDto).collect(Collectors.toList())
        );
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCart().getProducts().stream().map(Product::getProductPrice).reduce(BigDecimal.ZERO, BigDecimal::add),
                mapCartToCartDto(order.getCart())
        );
    }

}
