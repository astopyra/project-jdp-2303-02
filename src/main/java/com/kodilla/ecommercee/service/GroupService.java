package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
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

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(final Long id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Group with given id number couldn't be found"));
    }
}
