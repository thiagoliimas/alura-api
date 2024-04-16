package com.aluraapi.aluraapi.domain.user;

import com.aluraapi.aluraapi.infra.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity(name="users")
@Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames = {"email", "username"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, length = 20, nullable = false)
    @Pattern(regexp = "^[a-z]+$", message = "O atributo deve conter apenas letras minúsculas.")
    private String username;

    @Column(unique = true, nullable = false)
    @Email(message = "O email fornecido não é válido")
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
