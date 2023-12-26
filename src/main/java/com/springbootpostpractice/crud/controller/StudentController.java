package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.*;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.Projection.StudentPageProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import com.springbootpostpractice.crud.repository.StudentRepository;
import com.springbootpostpractice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StudentController
{
    @Autowired
    StudentService studentService;

    @GetMapping("/studentDetail")
    public List<StudentProjection> getStudents()
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
    @GetMapping("/studentNameAndRollno/{rollno}")
    public StudentProjection  getStudentNameDetail(@PathVariable String rollno) {
        return studentService.getStudentNameDetail(rollno);
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
    public List<studentDto> getStudentsByAge(@RequestParam int age)
    {
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








    @PostMapping("/student-save-em")
    public String saveStudentUsingEm(@RequestBody Student student) {
        return studentService.saveStudentUsingEm(student);
    }

    @GetMapping("/studentWithEM/{studentId}")
    public studDto getStudentDetailsWithEM(@PathVariable Integer studentId) {
        return studentService.getStudentDetailsWithEM(studentId);

    }
    @CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.PUT})
    // Update student by rollno
    @PutMapping("updateStudent/{rollno}")
    public void updateStudent(@PathVariable String rollno, @RequestBody studDto updatedStudent) {
        studentService.updateStudent(rollno, updatedStudent);

    }

    @GetMapping("/student-pagination")
    public Page<StudentProjection> getStudentDetailPagination(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String searchString) {

        Pageable pageable = PageRequest.of(page, size);
        return studentService.getStudentDetailPagination(searchString, pageable);
    }


    @PostMapping("/add-new-student")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequest studentRequest) {
        if ( studentService.isRollnoExists(studentRequest.getRollno())) {
            return ResponseEntity.badRequest().body("Rollno already exists");
        }

        studentService.saveStudentAndUser(studentRequest);
        return ResponseEntity.ok("Student and User added successfully");
    }


    @GetMapping("/student-profile-info/{rollno}")
    public ResponseEntity<studentDto> getOneStudentDetail(@PathVariable String rollno) {
        studentDto studenDto = studentService.getOneStudentDetail(rollno);
        if (studenDto != null) {
            return ResponseEntity.ok(studenDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}
