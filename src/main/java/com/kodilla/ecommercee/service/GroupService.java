package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group addGroup(final Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(final Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}
