package com.aluraapi.aluraapi.repositories;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.enrollment.Enrollment;
import com.aluraapi.aluraapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsEnrollmentByCourseIdAndUserId(Course courseId, User userId);
}
