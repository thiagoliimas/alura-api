package com.aluraapi.aluraapi.domain.courses;

import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.CourseDTO;
import com.aluraapi.aluraapi.infra.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "courses")
@Table(name = "courses", uniqueConstraints={@UniqueConstraint(columnNames = {"code"})})
@Getter
@Setter
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 20)
    private String code;

    @JoinColumn(nullable = false, name = "instructor_id")
    @ManyToOne
    private User instuctor;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(nullable = false)
    private LocalDateTime ts_creation;

    private LocalDateTime ts_inactivation;

    public Course (CourseDTO courseDTO, User user){
        this.name = courseDTO.name();
        this.code = courseDTO.code();
        this. instuctor = user;
        this.description = courseDTO.description();
        this.status = StatusEnum.ACTIVE;
        this.ts_creation = LocalDateTime.now();
    }
}
