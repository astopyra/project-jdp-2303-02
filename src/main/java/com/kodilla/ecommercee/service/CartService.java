package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public void createCart(final Cart toCreate) throws UserNotFoundException {
        Optional<User> cartOwner = userRepository.findById(toCreate.getUser().getId());
        if (cartOwner.isPresent()) {
            cartRepository.save(new Cart(
                    toCreate.getId(),
                    cartOwner.get(),
                    toCreate.getProducts())
            );
        } else {
            throw new UserNotFoundException("Cart cannot be created, because user with given ID: " +
                    toCreate.getUser().getId() + " does not exist.");
        }
    }

    public List<Product> getProductsFromCart(final Long id) throws CartNotFoundException {
        Optional<Cart> requestedCart = cartRepository.findById(id);

        if (requestedCart.isPresent()) {
            return requestedCart.get().getProducts();
        } else {
            throw new CartNotFoundException("Cannot find cart with given ID: " + id);
        }
    }

    public Cart addProductToCart(final Long productId, final Long cartId) throws CartNotFoundException, ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with given ID: " + productId));
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with given ID: " + cartId));

        product.getCarts().add(cart);
        cart.getProducts().add(product);

        productRepository.save(product);
        return cartRepository.save(cart);
    }

    public void removeProductFromCart(final Long productId, final Long cartId) throws CartNotFoundException, ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with given ID: " + productId));
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with given ID: " + cartId));

        product.getCarts().remove(cart);
        cart.getProducts().remove(product);

        productRepository.save(product);
        cartRepository.save(cart);
    }

    public Order createOrder(final Long cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with given ID: " + cartId));

        return orderRepository.save(new Order(cart));
    }
}
