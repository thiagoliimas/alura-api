package com.aluraapi.aluraapi.integrationtest;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.courses.StatusCourse;
import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.domain.user.UserRole;
import com.aluraapi.aluraapi.dtos.CourseDTO;
import com.aluraapi.aluraapi.dtos.UserDTO;
import com.aluraapi.aluraapi.repositories.CourseRepository;
import com.aluraapi.aluraapi.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CourseTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    @DisplayName("Should return successfully when searching for user by username")
    public void disableCourseSuccess() throws Exception {

        User user = userRepository.save(new User(new UserDTO(
                "Paulo",
                "paulosilva",
                "paulo@outlook.com",
                "abc123",
                UserRole.INSTRUCTOR)));

        Course course = courseRepository.save(new Course(new CourseDTO(
                "Java",
                "java",
                1L,
                "curso básico"), user));

        this.testRestTemplate.put("/courses/disable/java",Course.class);

        var result = courseRepository.findById(1L);

        assertNotNull(result);
        assertNull(course.getTs_inactivation());
        assertEquals(StatusCourse.ACTIVE, course.getStatus());
        assertEquals(StatusCourse.INACTIVE, result.get().getStatus());
        assertNotNull(result.get().getTs_inactivation());
    }
}
