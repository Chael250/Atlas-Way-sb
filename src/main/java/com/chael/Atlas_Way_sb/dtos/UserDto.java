package com.chael.Atlas_Way_sb.dtos;

import com.chael.Atlas_Way_sb.entities.enums.UserType;

public record UserDto(
        String username,
        String password,
        String email,
        String firstname,
        String lastname
) {
}
