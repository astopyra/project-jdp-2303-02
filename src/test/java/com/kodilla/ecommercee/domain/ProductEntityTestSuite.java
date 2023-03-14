package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductEntityTestSuite {



    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testSaveProduct(){
//        Given
        Product product1   = new Product();
        Product product2   = new Product();

//        When
        List<Product> result = productRepository.findAll();
        productRepository.save(product1);
        productRepository.save(product2);


//        Then
        assertEquals(2, productRepository.findAll().size());

//      CleanUp
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
    }

    @Test
    public void testFindAllProducts() {

//        Given
        Group group = new Group();
        Product product1   = new Product();
        Product product2   = new Product();
        Product product3   = new Product();
        Product product4   = new Product();

        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);


//         When
        List<Product> productList = productRepository.findAll();

//        Then
        assertEquals(4, productList.size());

//        CleanUp
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        productRepository.deleteById(product3.getId());
        productRepository.deleteById(product4.getId());
        groupRepository.deleteById(group.getId());


    }
    @Test
    public void testFindById(){
//        Given
        Product product1   = new Product();
        productRepository.save(product1);

//        When
        Optional<Product> result = productRepository.findById(product1.getId());

//        Then

        assertTrue( result.isPresent());
//         CleanUp
        productRepository.deleteById(product1.getId());
    }

    @Test
    public void testDeleteById() {
//        Given
        Product product1 = new Product();
        productRepository.save(product1);

//        When
        Long id = product1.getId();
        productRepository.deleteById(id);

//        Then
        assertEquals(0, productRepository.findAll().size());
        assertEquals(Optional.empty(), productRepository.findById(id));

//        CleanUp

    }

    @Test
    public void testAddProductToGroup() {
//        Given

        Group group1 = new Group();
        Group group2 = new Group();
        groupRepository.save(group1);
        groupRepository.save(group2);

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        group1.getProducts().add(product1);
        group1.getProducts().add(product2);
        group2.getProducts().add(product3);
        group2.getProducts().add(product1);
        group2.getProducts().add(product4);

//        When

        int productListGroup1 = group1.getProducts().size();
        int productListGroup2 = group2.getProducts().size();

//        Then
        assertEquals(2, productListGroup1);
        assertEquals(3, productListGroup2);

//        CleanUp
    groupRepository.deleteAll();
    productRepository.deleteAll();
    }

    @Test
    public void testAddProductsToCart()  {

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        cart1.getProducts().add(product1);
        cart1.getProducts().add(product3);
        cart1.getProducts().add(product2);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product4);

//        When

        int cart1ProductList = cart1.getProducts().size();
        int cart2ProductList = cart2.getProducts().size();
        System.out.println(cart1ProductList);

//        Then

        assertEquals(3, cart1ProductList);
        assertEquals(2, cart2ProductList);

//        CleanUp
        productRepository.deleteAll();
        cartRepository.deleteAll();

    }

}

