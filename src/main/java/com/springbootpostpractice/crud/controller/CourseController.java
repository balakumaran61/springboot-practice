package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.CourseDto;
import com.springbootpostpractice.crud.model.Course;
import com.springbootpostpractice.crud.repository.Projection.CourseProjection;
import com.springbootpostpractice.crud.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CourseController
    {
        @Autowired
        CourseService courseService;
        @PostMapping("/storeCourse")
        public CourseDto saveCourse(@RequestBody Course course)
        {
            return courseService.saveCourse(course);
        }

        @GetMapping("/view-course")
        public List<CourseProjection> getAllCourse()
        {
            return courseService.getAllCourse();
        }
        @PostMapping("/batch-update-course")
        public List<CourseDto> batchUpdateCourses(@RequestBody List<CourseDto> CourseDtoList)
        {
            return courseService.batchUpdateCourses( CourseDtoList);
        }
    }
