package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        User toCreate = userMapper.mapUserDtoToUser(userDto);
        return ResponseEntity.ok(userService.createUser(toCreate));
    }

    @PutMapping(value = "blockUserId={userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId) throws UserNotFoundException{
        return ResponseEntity.ok(userMapper.mapUserToUserDto(userService.blockUser(userId)));
    }

    @PutMapping(value = "{userId}")
    public ResponseEntity<String> generateKey(@PathVariable Long userId) throws UserNotFoundException{
        return ResponseEntity.ok(userService.generateKeyForUser(userId));
    }

}