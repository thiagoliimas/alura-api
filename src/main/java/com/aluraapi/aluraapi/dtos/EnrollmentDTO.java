package com.aluraapi.aluraapi.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EnrollmentDTO(UUID user, Long course, LocalDate date ) {
}
