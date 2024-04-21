package com.aluraapi.aluraapi.controllers;

import com.aluraapi.aluraapi.domain.enrollment.Enrollment;
import com.aluraapi.aluraapi.dtos.EnrollmentDTO;
import com.aluraapi.aluraapi.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService service;

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) throws Exception {
        Enrollment enrollment = this.service.createEnrollment(enrollmentDTO);
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }
}
