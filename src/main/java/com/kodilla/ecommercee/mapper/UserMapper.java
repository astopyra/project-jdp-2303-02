package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserMapper {

    public User mapUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.isLoggable(),
                new ArrayList<>()
        );
    }

    public UserDto mapUserToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.isUser_isLoggable()
        );
    }
}
