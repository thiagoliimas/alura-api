package com.aluraapi.aluraapi.dtos;

import java.time.LocalDate;

public record EnrollmentDTO(Long userId, Long courseId, LocalDate registrationDate) {
}
