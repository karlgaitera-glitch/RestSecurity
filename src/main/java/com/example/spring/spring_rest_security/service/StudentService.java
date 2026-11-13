package com.example.spring.spring_rest_security.service;

import java.util.List;

import com.example.spring.spring_rest_security.dto.StudentDto;

public interface StudentService {

    /**
     * Creates a new student record.
     *
     * @param thStudentDto the student data to save
     * @return the persisted {@link StudentDto}
     */
    StudentDto createStudent(StudentDto thStudentDto);

    /**
     * Retrieves a student by their unique identifier.
     *
     * @param theId the student ID
     * @return the matching {@link StudentDto}
     */
    StudentDto getStudentById(Long theId);

    /**
     * Retrieves all student records.
     *
     * @return a list of all {@link StudentDto} objects
     */
    List<StudentDto> getAllStudents();

    /**
     * Fully updates an existing student record.
     *
     * @param theId         the ID of the student to update
     * @param theStudentDto the updated student data
     * @return the updated {@link StudentDto}
     */
    StudentDto updateStudent(Long theId, StudentDto theStudentDto);

    /**
     * Partially updates an existing student record.
     *
     * @param theId         the ID of the student to patch
     * @param theStudentDto the partial student data
     * @return the updated {@link StudentDto}
     */
    StudentDto patchStudent(Long theId, StudentDto theStudentDto);

    /**
     * Deletes a student record by their unique identifier.
     *
     * @param theId the ID of the student to delete
     */
    void deleteStudentById(Long theId);
}
