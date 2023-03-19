package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        cartService.createCart(cartMapper.mapCartDtoToCart(cartDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable Long cartId) throws CartNotFoundException {
        List<Product> products = cartService.getProductsFromCart(cartId);
        return ResponseEntity.ok(cartMapper.mapProductsToDtoList(products));
    }

    @PutMapping(value = "addProduct/{productId}/{cartId}")
    public ResponseEntity<CartDto> addProductsToCart(@PathVariable Long productId, @PathVariable Long cartId) throws CartNotFoundException, ProductNotFoundException {
        Cart updatedCart = cartService.addProductToCart(productId, cartId);
        return ResponseEntity.ok(cartMapper.mapCartToCartDto(updatedCart));
    }

    @DeleteMapping(value = "deleteProducts/{productId}/{cartId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long productId, @PathVariable Long cartId) throws CartNotFoundException, ProductNotFoundException {
        cartService.removeProductFromCart(productId, cartId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{cartId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapOrderToOrderDto(cartService.createOrder(cartId)));
    }
}
