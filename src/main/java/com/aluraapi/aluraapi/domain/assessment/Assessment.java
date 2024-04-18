package com.aluraapi.aluraapi.domain.assessment;

import com.aluraapi.aluraapi.domain.courses.Course;
import com.aluraapi.aluraapi.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;


@Entity(name = "assessment")
@Table (name = "assessment", uniqueConstraints={@UniqueConstraint(columnNames = {"student_id", "course_id"})})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "student_id")
    @OneToOne
    private User student;

    @JoinColumn(name = "course_id")
    @OneToOne
    private Course course;

    @Column(nullable = false)
    @Range(min = 1, max = 10)
    private Integer grade;
}
