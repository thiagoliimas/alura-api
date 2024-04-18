package com.aluraapi.aluraapi.infra;

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
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", String.valueOf(HttpStatus.BAD_REQUEST.value()));
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
