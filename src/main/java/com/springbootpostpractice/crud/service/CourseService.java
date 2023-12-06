package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.controller.CourseController;
import com.springbootpostpractice.crud.dto.CourseDto;
import com.springbootpostpractice.crud.model.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService

{
    CourseDto saveCourse(Course course);
    public Course getCourseById(String courseCode);
}
