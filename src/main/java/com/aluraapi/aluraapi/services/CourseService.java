package com.aluraapi.aluraapi.services;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.CourseDTO;
import com.aluraapi.aluraapi.dtos.StatisticsCourseDTO;
import com.aluraapi.aluraapi.infra.StatusEnum;
import com.aluraapi.aluraapi.repositories.CourseRepository;
import com.aluraapi.aluraapi.repositories.StatisticsCoursesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private UserService userService;


    @Autowired
    private StatisticsCoursesRepository statisticsCoursesRepository;


    public Page<Course> getAllCourses(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public List<StatisticsCourseDTO> courseRanking() {
        return this.statisticsCoursesRepository.findCourseStatistics();
    }

    public void saveCourse(Course course){
        this.repository.save(course);
    }

    public Course createCourse (CourseDTO courseDTO) throws Exception {
        User user = this.userService.findUserById(courseDTO.instructorId());
        userService.codeIsValid(courseDTO);
        userService.isInstructor(user);

        Course course = new Course(courseDTO, user);
        this.saveCourse(course);
        return course;
    }

    public Course findCourseByCode(String code){
        return this.repository.findCourseByCode(code).orElseThrow(EntityNotFoundException::new);
    }

    public Course findCourseById(Long id){
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void disableCourse(String code) {
        Course course = this.findCourseByCode(code);
        course.setStatus(StatusEnum.INACTIVE);
        course.setTs_inactivation(LocalDateTime.now());
        this.repository.save(course);
    }

    public Page<Course> findCoursesByStatus(String status, Pageable pageable) {
        return this.repository.findCoursesByStatus(StatusEnum.valueOf(status), pageable);
    }
}
