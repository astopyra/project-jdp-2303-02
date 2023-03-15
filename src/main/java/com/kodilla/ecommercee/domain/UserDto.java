package com.kodilla.ecommercee.domain;

import lombok.Getter;

@Getter
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private boolean isLoggable;

    public UserDto(Long id, String name, String surname, boolean isLoggable) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isLoggable = isLoggable;
    }
}
