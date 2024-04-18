package com.aluraapi.aluraapi.repositories;

import com.aluraapi.aluraapi.domain.enrollment.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
