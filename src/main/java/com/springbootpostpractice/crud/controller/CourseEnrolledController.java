package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.CourseEnrolledDto;
import com.springbootpostpractice.crud.dto.GuardianDto;
import com.springbootpostpractice.crud.model.CourseEnrolled;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.service.CourseEnrolledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseEnrolledController {

    @Autowired
    CourseEnrolledService courseEnrolledService;

    @PostMapping("/saveCourseReg")
    public String saveCourseEnroll(@RequestBody CourseEnrolledDto courseEnrolledDto)
    {
        return courseEnrolledService.saveCourseEnroll(courseEnrolledDto);

    }
}
