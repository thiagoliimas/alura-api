package com.aluraapi.aluraapi.controllers;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.CourseDTO;
import com.aluraapi.aluraapi.dtos.UserDTO;
import com.aluraapi.aluraapi.infra.StatusEnum;
import com.aluraapi.aluraapi.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourse(@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursesPage = this.service.getAllCourses(pageable);
        return new ResponseEntity<>(coursesPage.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Course>> getCourseByStatus(@PathVariable("status") String status,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = this.service.findCoursesByStatus(status, pageable);
        return new ResponseEntity<>(coursePage.getContent(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO) throws Exception {
        Course newCourse = this.service.createCourse(courseDTO);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @PutMapping("/disable/{code}")
    public ResponseEntity disableCourse(@PathVariable("code") String code){
        service.disableCourse(code);
        return ResponseEntity.ok().build();
    }
}
