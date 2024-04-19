package com.aluraapi.aluraapi.controllers;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.dtos.CourseDTO;
import com.aluraapi.aluraapi.dtos.StatisticsCourseDTO;
import com.aluraapi.aluraapi.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/statistics")
    public List<StatisticsCourseDTO> courseRanking (){
        return service.courseRanking();
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourse(@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(this.service.getAllCourses(pageable).getContent(), HttpStatus.OK);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Course>> getCourseByStatus(@PathVariable("status") String status,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(this.service.findCoursesByStatus(status, pageable).getContent(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO) throws Exception {
        return new ResponseEntity<>(this.service.createCourse(courseDTO), HttpStatus.CREATED);
    }

    @PutMapping("/disable/{code}")
    public ResponseEntity disableCourse(@PathVariable("code") String code){
        service.disableCourse(code);
        return ResponseEntity.ok().build();
    }
}
