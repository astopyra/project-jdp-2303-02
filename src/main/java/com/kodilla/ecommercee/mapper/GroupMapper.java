package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.entity.Group;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public GroupDto mapGroupToGroupDto(Group group) {
        return new GroupDto(group.getId(), group.getGroupName());
    }

    public Group mapGroupDtoToGroup(GroupDto groupDto) {
        return new Group(groupDto.getId(), groupDto.getName(), new ArrayList<>());
    }

    public List<GroupDto> mapGroupListToGroupDtoList(final List<Group> groups) {
        return groups.stream()
                .map(this::mapGroupToGroupDto)
                .collect(Collectors.toList());
    }
}
