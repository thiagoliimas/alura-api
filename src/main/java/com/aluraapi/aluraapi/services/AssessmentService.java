package com.aluraapi.aluraapi.services;

import com.aluraapi.aluraapi.domain.assessment.Assessment;
import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.AssessmentDTO;
import com.aluraapi.aluraapi.infra.exceptions.GradeOutOfBoundsException;
import com.aluraapi.aluraapi.infra.exceptions.UnenrolledUserException;
import com.aluraapi.aluraapi.notification.EmailSender;
import com.aluraapi.aluraapi.notification.UnsatisfactoryAssessmentNotification;
import com.aluraapi.aluraapi.repositories.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Notification;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    public void saveAssessment (Assessment assessment){
        this.assessmentRepository.save(assessment);
    }

    public Assessment createAssessment(AssessmentDTO assessmentDTO) throws GradeOutOfBoundsException, UnenrolledUserException {
        if(assessmentDTO.grade() < 1 || assessmentDTO.grade() > 10){
            throw new GradeOutOfBoundsException();
        }

        User student = this.userService.findUserById(assessmentDTO.studentId());
        Course course = this.courseService.findCourseById(assessmentDTO.courseId());

        if(this.enrollmentService.existsEnrollmentByCourseIdAndUserId(student, course)){
            Assessment assessment = new Assessment(student, course, assessmentDTO.grade());
            UnsatisfactoryAssessmentNotification.instructorEmailSend(assessment);

            this.saveAssessment(assessment);
            return assessment;
        } else throw new UnenrolledUserException();
    }
}
