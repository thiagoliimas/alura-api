package com.aluraapi.aluraapi.repositories;

import com.aluraapi.aluraapi.dtos.StatisticsCourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsCoursesRepository extends JpaRepository<StatisticsCourseDTO, Long> {

    @Query(value = "SELECT e.course_id AS course_id, c.name AS course, e.num_ocorrencias AS occurrence, AVG(a.grade) AS score " +
            "FROM (" +
            "    SELECT course_id, COUNT(*) AS num_ocorrencias " +
            "    FROM enrollment " +
            "    GROUP BY course_id " +
            "    HAVING COUNT(*) > 3 " +
            ") AS e " +
            "JOIN courses AS c ON e.course_id = c.id " +
            "LEFT JOIN assessment AS a ON e.course_id = a.course_id " +
            "GROUP BY e.course_id, c.name, e.num_ocorrencias", nativeQuery = true)
    List<StatisticsCourseDTO> findCourseStatistics();
}
