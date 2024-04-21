package com.aluraapi.aluraapi.integrationtest;

import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.domain.user.UserRole;
import com.aluraapi.aluraapi.dtos.UserDTO;
import com.aluraapi.aluraapi.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("Should return successfully when searching for user by username")
    public void getByUsernameSuccess() {
        User user = repository.save(new User(
                new UserDTO(
                        "Thiago",
                        "thiagoliimas",
                        "thiagoliimas@outlook.com",
                        "abc123",
                        UserRole.STUDENT)));
        var result = this.testRestTemplate.getForEntity("/users?username=thiagoliimas",User.class);
        assertNotNull(user);
        assertEquals(user.getName(), result.getBody().getName());
        assertEquals(UserRole.STUDENT, result.getBody().getRole());
        assertEquals(user.getEmail(), result.getBody().getEmail());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("Should return NOT_FOUND when not finding user by username")
    public void getByUsernameFailure() {
        User user = repository.save(new User(
                new UserDTO(
                "Paulo",
                "paulosilva",
                "paulo@outlook.com",
                "abc123",
                UserRole.STUDENT)));
        var result = this.testRestTemplate.getForEntity("/users?username=outro",User.class);
        assertNotNull(user);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
