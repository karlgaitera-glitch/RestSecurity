package com.example.spring.spring_rest_security.exception;

public class StudentNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -2690100046087976236L;

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(String message){
        super(message);
    }

    public StudentNotFoundException(Throwable cause){
        super(cause);
    }
}
