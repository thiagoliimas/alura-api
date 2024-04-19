package com.aluraapi.aluraapi.repositories;

import com.aluraapi.aluraapi.domain.assessment.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
