package com.example.spring.spring_rest_security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.spring.spring_rest_security.dto.StudentDto;
import com.example.spring.spring_rest_security.entity.Student;
import com.example.spring.spring_rest_security.exception.StudentNotFoundException;
import com.example.spring.spring_rest_security.mapper.StudentMapper;
import com.example.spring.spring_rest_security.repository.StudentRepository;

/**
 * Default implementation of {@link StudentService} backed by a JPA repository.
 */
@Service
public class StudentServiceImplement implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImplement(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDto createStudent(StudentDto theStudentDto) {
        Student theStudent = StudentMapper.mapToStudent(theStudentDto);
        Student saved = studentRepository.save(theStudent);
        return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * {@inheritDoc}
     *
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    @Override
    public StudentDto getStudentById(Long theId) {
        Student student = studentRepository.findById(theId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student id" + theId + " not found!"));
        return StudentMapper.mapToStudentDto(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(
            (student)-> StudentMapper.mapToStudentDto(student)
        ).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    @Override 
    public StudentDto updateStudent(Long theId, StudentDto theStudentDto){
        Student student = studentRepository.findById(theId)
            .orElseThrow(()-> new StudentNotFoundException(
                "Student id" + theId + " not found!"
            ));

            student.setFirstName(theStudentDto.getFirstName());
            student.setLastName(theStudentDto.getLastName());
            student.setEmail(theStudentDto.getEmail());

            Student saved = studentRepository.save(student);
            return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * {@inheritDoc}
     *
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    @Override 
    public StudentDto patchStudent(Long theId, StudentDto theStudentDto){
        Student student = studentRepository.findById(theId)
            .orElseThrow(()->new StudentNotFoundException(
                "Student id" + theId + " not found!"));
        
        if(theStudentDto.getFirstName()!=null){
            student.setFirstName(theStudentDto.getFirstName());
        }
        if(theStudentDto.getLastName()!=null){
            student.setLastName(theStudentDto.getLastName());
        }
        if(theStudentDto.getEmail()!=null){
            student.setEmail(theStudentDto.getEmail());
        }

        Student saved = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * {@inheritDoc}
     *
     * @throws StudentNotFoundException if no student exists with the given ID
     */
    @Override
    public void deleteStudentById(Long theId){
        studentRepository.findById(theId)
        .orElseThrow(()->new StudentNotFoundException(
            "Student id " + theId + "not found!"
        ));
        studentRepository.deleteById(theId);
    }
}
