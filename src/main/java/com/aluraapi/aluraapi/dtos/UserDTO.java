package com.aluraapi.aluraapi.dtos;

import com.aluraapi.aluraapi.infra.RoleEnum;

public record UserDTO(String name, String username, String email, String password, RoleEnum role) {
}
