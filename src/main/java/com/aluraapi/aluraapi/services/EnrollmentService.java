package com.aluraapi.aluraapi.services;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.enrollment.Enrollment;
import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.EnrollmentDTO;
import com.aluraapi.aluraapi.domain.courses.StatusCourse;
import com.aluraapi.aluraapi.infra.exceptions.AlreadyRegisteredUserException;
import com.aluraapi.aluraapi.infra.exceptions.InactiveCourseException;
import com.aluraapi.aluraapi.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    public List<Enrollment> getAllEnrollment(){
        return enrollmentRepository.findAll();
    }

    public void saveEnrollment (Enrollment enrollment){
        this.enrollmentRepository.save(enrollment);
    }

    public Enrollment createEnrollment(EnrollmentDTO enrollmentDTO) throws Exception {
        User user = this.userService.findUserById(enrollmentDTO.userId());
        Course course = this.courseService.findCourseById(enrollmentDTO.courseId());
        try{

            if(course.getStatus() == StatusCourse.ACTIVE){
                Enrollment enrollment = new Enrollment(user, course, enrollmentDTO.registrationDate());
                this.saveEnrollment(enrollment);
                return enrollment;
            } else throw new InactiveCourseException();

        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyRegisteredUserException();
        }
    }

    public boolean existsEnrollmentByCourseIdAndUserId(User student, Course course){
        return this.enrollmentRepository.existsEnrollmentByCourseIdAndUserId(course, student);
    }
}
