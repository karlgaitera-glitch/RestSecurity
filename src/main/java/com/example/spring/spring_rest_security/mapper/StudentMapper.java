package com.example.spring.spring_rest_security.mapper;

import com.example.spring.spring_rest_security.dto.StudentDto;
import com.example.spring.spring_rest_security.entity.Student;

public class StudentMapper {

    /**
     * Converts a {@link Student} entity to a {@link StudentDto}.
     *
     * @param theStudent the entity to convert
     * @return the corresponding {@link StudentDto}
     */
    public static StudentDto mapToStudentDto(Student theStudent) {
        return new StudentDto(theStudent.getId(),
                theStudent.getFirstName(),
                theStudent.getLastName(),
                theStudent.getEmail());
    }

    /**
     * Converts a {@link StudentDto} to a {@link Student} entity.
     *
     * @param theStudentDto the DTO to convert
     * @return the corresponding {@link Student} entity
     */
    public static Student mapToStudent(StudentDto theStudentDto) {
        return new Student(theStudentDto.getId(),
                theStudentDto.getFirstName(),
                theStudentDto.getLastName(),
                theStudentDto.getEmail());
    }
}
