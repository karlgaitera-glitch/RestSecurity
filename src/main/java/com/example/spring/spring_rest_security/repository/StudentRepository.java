package com.example.spring.spring_rest_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.spring_rest_security.entity.Student;

/**
 * Spring Data JPA repository for {@link Student} entities.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

}
