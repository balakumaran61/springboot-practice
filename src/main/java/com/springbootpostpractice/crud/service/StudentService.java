package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.*;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.Projection.StudentPageProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface StudentService
{
    Student create(Student student);

    public Page<StudentPageProjection> getStudentPage(Pageable pageble);
    List<StudentProjection> getAllStudents();
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


     String saveStudentUsingEm(Student student);
    studDto getStudentDetailsWithEM(Integer studentId);

    public void updateStudent(@PathVariable String rollno, @RequestBody studDto updatedStudent);

    public StudentProjection  getStudentNameDetail(String rollno);


    //
    public Page<StudentProjection> getStudentDetailPagination(String searchString,Pageable pageable);
    void saveStudentAndUser(StudentRequest studentRequest);

    boolean isRollnoExists(String rollno);

    Student getStudentByRollNo(String rollNo);
    studentDto getOneStudentDetail(String rollno);


}
