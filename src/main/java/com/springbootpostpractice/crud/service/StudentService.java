package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.StudentUpdateDto;
import com.springbootpostpractice.crud.dto.studentDto;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StudentService
{
    Student create(Student student);
    List<Student> getAllStudents();
    Student getOneStudent(Integer id);
    studentDto getStudentDetailsById(Integer id);
    List<studentDto> getStudentByAgeGreaterThan(int age);
    List<StudentProjection> getStudentsWithAgeLessThan(int age);
    List<studentDto> getStudentsByAge(int age);
    Student getStudentById(Integer studentId);
    studentDto getStudentsByGuardianId(Integer guardianId);
    studentDto updateStudent(Integer studentId, StudentUpdateDto studentUpdateDto);

    String updateStudentEmail(Integer studentId, String email);
    List<studentDto> batchUpdateStudents(List<studentDto> studentDtoList);



}
