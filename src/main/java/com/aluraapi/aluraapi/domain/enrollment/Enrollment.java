package com.aluraapi.aluraapi.domain.enrollment;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.EnrollmentDTO;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity(name = "enrollment")
@Table (name = "enrollment", uniqueConstraints={@UniqueConstraint(columnNames = {"user", "course"})})
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
    @Column(nullable = false)
    private User user;

    @ManyToOne
    @Column(nullable = false)
    private Course course;

    @Column(nullable = false)
    @Pattern(regexp = "dd/MM/yyyy")
    private LocalDate date;

    public Enrollment (User user, Course course, LocalDate date){
        this.user = user;
        this.course = course;
        this.date = date;
    }
}
