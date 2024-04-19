package com.aluraapi.aluraapi.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EnrollmentDTO(Long userId, Long courseId, LocalDate registrationDate) {
}
