package com.aluraapi.aluraapi.dtos;

import com.aluraapi.aluraapi.domain.user.UserRole;

public record UserDTO(String name, String username, String email, String password, UserRole role) {
}
