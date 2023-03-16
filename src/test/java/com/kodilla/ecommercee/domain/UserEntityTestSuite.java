package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class UserEntityTestSuite {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testUserFindAll() {
        //Given & When
        userRepository.save(new User());
        userRepository.save(new User());
        userRepository.save(new User());

        //Then
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    public void testSaveUser() {
        //Given & When
        userRepository.save(new User());
        userRepository.save(new User());
        userRepository.save(new User());

        //Then
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    public void testDeleteUserById() {
        //Given
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //When
        int sizeBeforeDelete = userRepository.findAll().size();
        userRepository.deleteById(user2.getId());
        int sizeAfterDelete = userRepository.findAll().size();

        //Then
        assertEquals(3, sizeBeforeDelete);
        assertEquals(2, sizeAfterDelete);
    }

    @Test
    public void testFindUserById() {
        //Given & When
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //Then
        assertEquals(user1, userRepository.findById(user1.getId()).orElse(null));
    }

    @Test
    public void testCreateuserWithCarts() {
        //Given
        User user = new User();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        //When
        user.getCarts().add(cart1);
        user.getCarts().add(cart2);
        userRepository.save(user);

        //Then
        assertEquals(1, userRepository.findAll().size());
        assertEquals(2, cartRepository.findAll().size());
    }
}
