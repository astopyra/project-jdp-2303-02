package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAll();
    Optional<Group> findById(Long id);
    void deleteById(Long id);
    Group save(Group group);
}
