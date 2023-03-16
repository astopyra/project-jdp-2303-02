package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupEntityTestSuite {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveGroup() {
        //Given
        Group group1 = new Group();
        Group group2 = new Group();

        //When
        groupRepository.save(group1);
        groupRepository.save(group2);

        //Then
        assertEquals(2, groupRepository.findAll().size());
    }

    @Test
    public void testFindAllGroups() {
        //Given
        Group group1 = new Group();
        Group group2 = new Group();

        //When
        groupRepository.save(group1);
        groupRepository.save(group2);
        List<Group> groups = groupRepository.findAll();

        //Then
        assertEquals(2, groups.size());
    }

    @Test
    public void testDeleteGroupById() {
        //Given
        Group group1 = new Group();
        Group group2 = new Group();
        //When
        groupRepository.save(group1);
        groupRepository.save(group2);
        int savedSize = groupRepository.findAll().size();

        groupRepository.deleteById(group1.getId());
        groupRepository.deleteById(group2.getId());
        int deletedSize = groupRepository.findAll().size();

        //Then
        assertEquals(2, savedSize);
        assertEquals(0, deletedSize);
    }

    @Test
    public void testCreateGroupWithProducts() {
        //Given
        Group group = new Group();
        Product product1 = new Product();
        Product product2 = new Product();

        //When
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        groupRepository.save(group);

        //Then
        assertEquals(1, groupRepository.findAll().size());
        assertEquals(2, productRepository.findAll().size());
    }

    @Test
    public void testFindGroupById() {
        //Given
        Group group1 = new Group();
        Group group2 = new Group();

        //When
        groupRepository.save(group1);
        groupRepository.save(group2);

        //Then
        assertEquals(group1, groupRepository.findById(group1.getId()).orElse(null));
        assertEquals(group2, groupRepository.findById(group2.getId()).orElse(null));
    }
}
