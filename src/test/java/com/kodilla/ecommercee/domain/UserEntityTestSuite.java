package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserEntityTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserEntitySave() {
        //Given
        User user = new User();

        //When
        List<User> users = userRepository.findAll();
        userRepository.save(user);

        //Then
        assertEquals(users.size()+1, userRepository.findAll().size());

        //Cleanup
        userRepository.delete(new User());
    }


}
