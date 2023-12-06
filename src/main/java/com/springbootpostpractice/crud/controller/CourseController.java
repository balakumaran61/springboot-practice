package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.CourseDto;
import com.springbootpostpractice.crud.model.Course;
import com.springbootpostpractice.crud.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    }
