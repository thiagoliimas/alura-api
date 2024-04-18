package com.aluraapi.aluraapi.dtos;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.user.User;

import java.util.UUID;

public record AssessmentDTO (UUID studentId, Long courseID, Integer grade) {
}
