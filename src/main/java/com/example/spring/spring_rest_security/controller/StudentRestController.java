package com.example.spring.spring_rest_security.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.spring_rest_security.dto.StudentDto;
import com.example.spring.spring_rest_security.service.StudentService;

/**
 * REST controller for managing student resources.
 */
@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Creates a new student record.
     *
     * @param theStudentDto the validated student payload
     * @return the created {@link StudentDto} with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<StudentDto> createStudents(
            @Valid @RequestBody StudentDto theStudentDto) {
        StudentDto saved = studentService.createStudent(theStudentDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Retrieves a single student by ID.
     *
     * @param id the unique student identifier
     * @return the matching {@link StudentDto} with HTTP 200 status
     */
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Retrieves all student records.
     *
     * @return a list of all {@link StudentDto} objects with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Fully updates an existing student record by ID.
     *
     * @param id            the unique student identifier
     * @param theStudentDto the updated student payload
     * @return the updated {@link StudentDto} with HTTP 200 status
     */
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
            @Valid @RequestBody StudentDto theStudentDto) {
        StudentDto studentDto = studentService.updateStudent(
                id, theStudentDto);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Partially updates an existing student record by ID.
     *
     * @param id           the unique student identifier
     * @param thStudentDto the partial student payload
     * @return the updated {@link StudentDto} with HTTP 200 status
     */
    @PatchMapping("{id}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Long id,
            @Valid @RequestBody StudentDto thStudentDto) {
        StudentDto studentDto = studentService.patchStudent(id, thStudentDto);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Deletes a student record by ID.
     *
     * @param id the unique student identifier
     * @return a confirmation message with HTTP 200 status
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudentByIdEntity(
            @PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
