package com.aluraapi.aluraapi.domain.enrollment;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "enrollment")
@Table (name = "enrollment", uniqueConstraints={@UniqueConstraint(columnNames = {"user_id", "course_id"})})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "course_id")
    private Course courseId;

    @Column(nullable = false)
    private LocalDate registrationDate;

    public Enrollment (User userId, Course courseId, LocalDate registrationDate){
        this.userId = userId;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
    }
}
