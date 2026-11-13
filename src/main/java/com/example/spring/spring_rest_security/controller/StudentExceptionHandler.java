package com.example.spring.spring_rest_security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.spring.spring_rest_security.entity.StudentErrorResponse;
import com.example.spring.spring_rest_security.exception.StudentNotFoundException;


/**
 * Global exception handler for REST API endpoints in the application.
 */
@ControllerAdvice
public class StudentExceptionHandler {

    /**
     * Handles {@link StudentNotFoundException} and builds a 404 response.
     *
     * @param ex the thrown {@link StudentNotFoundException}
     * @return a {@link ResponseEntity} containing error details and 404 status
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(
            StudentNotFoundException ex) {
        StudentErrorResponse err = new StudentErrorResponse();

        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(ex.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles generic exceptions and returns a 400 Bad Request response.
     *
     * @param ex the thrown {@link Exception}
     * @return a {@link ResponseEntity} containing error details and 400 status
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(
            Exception ex) {
        StudentErrorResponse err = new StudentErrorResponse();

        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage("Invalid data. Only integers are accepted.");
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles bean validation failures and maps field names to error messages.
     *
     * @param ex the thrown {@link MethodArgumentNotValidException}
     * @return a {@link ResponseEntity} with field errors and 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {
        Map<String, String> err = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->{
            err.put(error.getField(), error.getDefaultMessage());
        });        
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
