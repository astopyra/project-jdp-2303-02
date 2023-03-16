package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.entity.Group;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupMapper {

    public GroupDto mapGroupToGroupDto(Group group) {
        return new GroupDto(group.getId(), group.getGroupName());
    }

    public Group mapGroupDtoToGroup(GroupDto groupDto) {
        return new Group(groupDto.getId(), groupDto.getName(), new ArrayList<>());
    }
}
