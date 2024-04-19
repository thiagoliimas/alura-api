package com.aluraapi.aluraapi.domain.user;

import com.aluraapi.aluraapi.dtos.UserDTO;
import com.aluraapi.aluraapi.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name="users")
@Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames = {"email", "username"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private String name;

    @Column(unique = true, length = 20, nullable = false)
    @Pattern(regexp = "^[a-z]+$", message = "O atributo deve conter apenas letras minúsculas.")
    private String username;

    @Column(unique = true, nullable = false)
    @Email(message = "O email fornecido não é válido")
    @JsonView(Views.Public.class)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private UserRole role;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public User (UserDTO userDTO){
        this.name = userDTO.name();
        this.username = userDTO.username();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.role = userDTO.role();
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );
        } else if (this.role == UserRole.INSTRUCTOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
        } else return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
