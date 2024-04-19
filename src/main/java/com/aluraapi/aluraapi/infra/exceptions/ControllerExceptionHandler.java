package com.aluraapi.aluraapi.infra.exceptions;

import com.aluraapi.aluraapi.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity treatDuplicationEntry (DataIntegrityViolationException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Já existe registro na base",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(AlreadyRegisteredUserException.class)
    public ResponseEntity treatDuplicationEntry (AlreadyRegisteredUserException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Já existe uma inscrição para este usuário neste curso",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(InactiveCourseException.class)
    public ResponseEntity treatDuplicationEntry (InactiveCourseException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Curso inativo",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(UnenrolledUserException.class)
    public ResponseEntity treatDuplicationEntry (UnenrolledUserException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Aluno não matriculado neste curso",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(GradeOutOfBoundsException.class)
    public ResponseEntity treatDuplicationEntry (GradeOutOfBoundsException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("A nota deve ser de 1 a 10",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity treatDuplicationEntry (InvalidUsernameException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Username deve conter apenas caracteres minúsculos, sem numerais e sem espaços",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treat404 (EntityNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity treatGeneralException (Exception e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
