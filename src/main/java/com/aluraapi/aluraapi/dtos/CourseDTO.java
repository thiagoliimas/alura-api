package com.aluraapi.aluraapi.dtos;

import com.aluraapi.aluraapi.domain.user.User;

import java.util.UUID;

public record CourseDTO (String name, String code, Long instructorId, String description) {
}
