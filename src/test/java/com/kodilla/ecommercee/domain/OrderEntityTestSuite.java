package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderEntityTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testOrderEntitySave() {
        //Given
        Order order = new Order();

        //When
        List<Order> orders = orderRepository.findAll();
        Order savedOrder = orderRepository.save(order);

        //Then
        assertEquals(orders.size() + 1, orderRepository.findAll().size());

        //CleanUp
        orderRepository.deleteById(savedOrder.getId());
    }

    @Test
    public void testOrderEntityFindAll() {
        //Given
        Order firstOrder = orderRepository.save(new Order());
        Order secondOrder = orderRepository.save(new Order());
        Order thirdOrder = orderRepository.save(new Order());

        //When
        List<Order> orders = orderRepository.findAll();

        //Then
        assertEquals(3, orders.size());

        //CleanUp
        orderRepository.deleteById(firstOrder.getId());
        orderRepository.deleteById(secondOrder.getId());
        orderRepository.deleteById(thirdOrder.getId());
    }

    @Test
    public void testOrderEntityFindById() {
        //Given
        Order savedOrder1 = orderRepository.save(new Order());
        Order savedOrder2 = orderRepository.save(new Order());
        Order savedOrder3 = orderRepository.save(new Order());

        //When
        Optional<Order> optionalOrder = orderRepository.findById(savedOrder2.getId());

        //Then
        assertTrue(optionalOrder.isPresent());

        //CleanUp
        orderRepository.deleteById(savedOrder1.getId());
        orderRepository.deleteById(savedOrder2.getId());
        orderRepository.deleteById(savedOrder3.getId());
    }

    @Test
    public void testOrderEntityDeleteById() {
        //Given
        Order savedOrder1 = orderRepository.save(new Order());

        //When
        List<Order> orders = orderRepository.findAll();
        orderRepository.deleteById(savedOrder1.getId());

        //Then
        assertEquals(orders.size() - 1, orderRepository.findAll().size());

        //CleanUp
    }
}
