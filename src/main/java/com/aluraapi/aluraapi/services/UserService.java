package com.aluraapi.aluraapi.services;

import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.CourseDTO;
import com.aluraapi.aluraapi.dtos.UserDTO;
import com.aluraapi.aluraapi.infra.RoleEnum;
import com.aluraapi.aluraapi.infra.exceptions.InvalidUsernameException;
import com.aluraapi.aluraapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findUserByUsername(String username) throws Exception {
        return this.repository.findUserByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public User findUserById(UUID id) throws Exception {
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void save(User user){
        this.repository.save(user);
    }

    public User createUser (UserDTO userDTO) throws InvalidUsernameException {
        if(!userDTO.username().matches("^[a-z]+$")){
            throw new InvalidUsernameException();
        }
        User newUser = new User(userDTO);
        this.save(newUser);
        return newUser;
    }

    public void isInstructor(User user) throws Exception {
        if(user.getRole() != RoleEnum.INSTRUCTOR){
            throw new Exception("Usuário não é um instrutor");
        }
    }

    public void isAdmin(User user) throws Exception {

        if(user.getRole() != RoleEnum.ADMIN){
            throw new Exception("Usuário não tem autorização para prosseguir com a solicitação");
        }
    }

    public void codeIsValid(CourseDTO courseDTO) throws Exception {
        if(!courseDTO.code().matches("^[a-zA-Z]+(?:-[a-zA-Z]+)*$")){
            throw new Exception("Código inválido.");
        }
    }
}
