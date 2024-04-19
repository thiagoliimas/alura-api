package com.aluraapi.aluraapi.controllers;

import com.aluraapi.aluraapi.domain.assessment.Assessment;
import com.aluraapi.aluraapi.dtos.AssessmentDTO;
import com.aluraapi.aluraapi.infra.exceptions.GradeOutOfBoundsException;
import com.aluraapi.aluraapi.infra.exceptions.UnenrolledUserException;
import com.aluraapi.aluraapi.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    @Autowired
    private AssessmentService service;

    @PostMapping
    public ResponseEntity<Assessment> createAssessment(@RequestBody AssessmentDTO assessmentDTO)
            throws GradeOutOfBoundsException, UnenrolledUserException {
        return new ResponseEntity<>(this.service.createAssessment(assessmentDTO), HttpStatus.CREATED);
    }
}
