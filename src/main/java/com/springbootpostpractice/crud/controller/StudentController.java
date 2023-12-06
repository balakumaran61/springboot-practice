package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.StudentUpdateDto;
import com.springbootpostpractice.crud.dto.studentDto;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import com.springbootpostpractice.crud.repository.StudentRepository;
import com.springbootpostpractice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController
{
    @Autowired
    StudentService studentService;



    @GetMapping("/student")
    public List<Student> getStudents()
    {
        return studentService.getAllStudents();
    }
    @PostMapping("/store")
    public Student saveStudent(@RequestBody Student student)
    {
        return studentService.create(student);
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id)
    {
        return studentService.getOneStudent(id);
    }

    @GetMapping("/studentName/{id}")
    public studentDto getStudentName(@PathVariable Integer id) {
        return studentService.getStudentDetailsById(id);
    }

    @GetMapping("/byAgeGreaterThan")
    public List<studentDto> getStudentByAgeGreaterThan(@RequestParam int age) {
        return studentService.getStudentByAgeGreaterThan(age);
    }
    @GetMapping("/students/namesAndRollnos")
    public List<StudentProjection> getStudentsWithAgeLessThan(@RequestParam int age) {
        return studentService.getStudentsWithAgeLessThan(age);
    }
    @GetMapping("/students/byAge")
    public List<studentDto> getStudentsByAge(@RequestParam int age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/students/{guardianId}")
    public studentDto getStudentsByGuardianId(@PathVariable Integer guardianId) {
        return studentService.getStudentsByGuardianId(guardianId);
    }
    @PostMapping("/update/{studentId}")
    public studentDto updateStudent(
            @PathVariable Integer studentId,
            @RequestBody StudentUpdateDto studentUpdateDto)
    {
        return studentService.updateStudent(studentId, studentUpdateDto);
    }
    @PostMapping("/updateEmail/{studentId}")
    public String updateStudentEmail(
            @PathVariable Integer studentId,
            @RequestBody StudentUpdateDto studentUpdateDto
    ) {
        return studentService.updateStudentEmail(studentId, studentUpdateDto.getEmail());
    }
    @PostMapping("/batch-update")
    public List<studentDto> batchUpdateStudents(@RequestBody List<studentDto> studentDtoList)
    {
        return studentService.batchUpdateStudents(studentDtoList);
    }




}
