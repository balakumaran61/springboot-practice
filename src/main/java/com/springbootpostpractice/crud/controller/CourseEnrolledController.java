package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.CourseEnrolledDto;
import com.springbootpostpractice.crud.dto.GuardianDto;
import com.springbootpostpractice.crud.dto.StudentNameRollno;
import com.springbootpostpractice.crud.model.CourseEnrolled;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.service.CourseEnrolledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseEnrolledController {

    @Autowired
    CourseEnrolledService courseEnrolledService;

    @PostMapping("/saveCourseReg")
    public String saveCourseEnroll(@RequestBody CourseEnrolledDto courseEnrolledDto)
    {
        return courseEnrolledService.saveCourseEnroll(courseEnrolledDto);

    }
    @GetMapping("/student-enrolled/{courseId}")
    public List<StudentNameRollno> getStudentsByCourseId(@PathVariable Integer courseId) {
        return courseEnrolledService.getStudentsByCourseId(courseId);
    }
}
