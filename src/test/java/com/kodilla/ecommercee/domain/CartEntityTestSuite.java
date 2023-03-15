package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartEntityTestSuite {


    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testSaveCart(){
//        Given
        Cart cart1 = new Cart();
        cartRepository.save(cart1);

//        When
        Long cart1Id = cart1.getId();
        Optional<Cart> savedCart1 = cartRepository.findById(cart1Id);

//        Then
        Assert.assertTrue(savedCart1.isPresent());
    }

    @Test
    public void testDeleteCardById() {
//        Given
        Cart cart1 = new Cart();
        cartRepository.save(cart1);
        Long cart1Id = cart1.getId();

//        When
        cartRepository.deleteById(cart1Id);
        int availableCart = cartRepository.findAll().size();

//        Then
        assertEquals(0,availableCart);
    }

    @Test
    public void testFindAllCarts() {
//        Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);
//        When
        List<Cart> carts = cartRepository.findAll();

//        Then
        assertEquals(3,carts.size());
    }

    @Test
    public void testFindCartById() {
//        Given
        Cart cart1 = new Cart();
        cartRepository.save(cart1);

//        When
        Long cartId = cart1.getId();
        Optional<Cart> findCart = cartRepository.findById(cartId);

//        Then
        assertNotNull(findCart);
        assertEquals(cartId, cart1.getId());
    }

    @Test
    public void testAddProductsToCart() {
//        Given
        Cart cart1 = new Cart();
        cartRepository.save(cart1);

        Product product1 = new Product();
        Product product2 = new Product();
        productRepository.save(product1);
        productRepository.save(product2);

//        When
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);
        int result = cartRepository.findById(cart1.getId()).get().getProducts().size();
//        Then
        assertEquals(2, result);
    }

    @Test
    public void testFindAllProductsInTheCart() {
//        Given
        Cart cart1 = new Cart();
        cartRepository.save(cart1);

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

//        When
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);
        cart1.getProducts().add(product3);

        int productsInCart = cartRepository.findById(cart1.getId()).get().getProducts().size();

//        Then
        assertEquals(3, productsInCart);

    }

 }

