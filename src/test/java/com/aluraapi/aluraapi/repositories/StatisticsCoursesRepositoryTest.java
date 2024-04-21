package com.aluraapi.aluraapi.repositories;

import com.aluraapi.aluraapi.domain.assessment.Assessment;
import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.enrollment.Enrollment;
import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.domain.user.UserRole;
import com.aluraapi.aluraapi.dtos.CourseDTO;
import com.aluraapi.aluraapi.dtos.StatisticsCourseDTO;
import com.aluraapi.aluraapi.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class StatisticsCoursesRepositoryTest {

    @Autowired
    private EntityManager manager;

    @Autowired
    private UserRepository userService;

    @Autowired
    private CourseRepository courseService;

    @Autowired
    private StatisticsCoursesRepository statisticsCoursesRepository;


    @Test
    @DisplayName("Should get Course Statitic successfully from DB")
    void findCourseStatisticsSuccess() {

        createStatistic();

        List<StatisticsCourseDTO> statisticsCourseList = statisticsCoursesRepository.findCourseStatistics();
        System.out.println(statisticsCourseList.get(0).getCourse());

        assertThat(statisticsCourseList.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Should not get Statistic from DB when statistic not exists")
    void findCourseStatisticsFailure() {

        List<StatisticsCourseDTO> statisticsCourseList = statisticsCoursesRepository.findCourseStatistics();

        assertThat(statisticsCourseList.isEmpty()).isTrue();
    }

    private void createStatistic(){
        this.manager.persist(new User(new UserDTO("Thiago", "thiagolima","thiago@mail.com","abc123", UserRole.INSTRUCTOR)));
        this.manager.persist(new User(new UserDTO("Paulo", "paulosilva","paulo@mail.com","abc123", UserRole.STUDENT)));
        this.manager.persist(new User(new UserDTO("Nayane", "nayanetavares","nayane@mail.com","abc123", UserRole.STUDENT)));
        this.manager.persist(new User(new UserDTO("Kelly", "kellytavares","kelly@mail.com","abc123", UserRole.STUDENT)));
        this.manager.persist(new User(new UserDTO("Misael", "misaellucas","misael@mail.com","abc123", UserRole.STUDENT)));

        Optional<User> user1 = this.userService.findById(1L);

        this.manager.persist(new Course(new CourseDTO("Java","java",1L,"Curso Básico"), user1.get()));

        Optional<Course> course = courseService.findById(1L);

        Optional<User> user2 = this.userService.findById(2L);
        Optional<User> user3 = this.userService.findById(3L);
        Optional<User> user4 = this.userService.findById(4L);
        Optional<User> user5 = this.userService.findById(5L);

        LocalDate data = LocalDate.now();

        this.manager.persist(new Enrollment(user2.get(), course.get(), data));
        this.manager.persist(new Enrollment(user3.get(), course.get(), data));
        this.manager.persist(new Enrollment(user4.get(), course.get(), data));
        this.manager.persist(new Enrollment(user5.get(), course.get(), data));

        this.manager.persist(new Assessment(user2.get(), course.get(), 7));
        this.manager.persist(new Assessment(user3.get(), course.get(), 6));
        this.manager.persist(new Assessment(user4.get(), course.get(), 9));
        this.manager.persist(new Assessment(user5.get(), course.get(), 8));

        statisticsCoursesRepository.findCourseStatistics();
    }
}