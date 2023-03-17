package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
@CrossOrigin

public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<Group> groupsList = groupService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapGroupListToGroupDtoList(groupsList));
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        Group group = groupService.getGroupById(groupId);
        return ResponseEntity.ok(groupMapper.mapGroupToGroupDto(group));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapGroupDtoToGroup(groupDto);
        groupService.saveGroup(group);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapGroupDtoToGroup(groupDto);
        Group savedGroup = groupService.saveGroup(group);
        return ResponseEntity.ok(groupMapper.mapGroupToGroupDto(savedGroup));
    }
}