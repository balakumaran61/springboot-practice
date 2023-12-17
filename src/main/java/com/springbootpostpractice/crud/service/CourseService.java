package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.controller.CourseController;
import com.springbootpostpractice.crud.dto.CourseDto;
import com.springbootpostpractice.crud.model.Course;
import com.springbootpostpractice.crud.repository.Projection.CourseProjection;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface CourseService

{
    CourseDto saveCourse(Course course);
    public Course getCourseById(String courseCode);
    List<CourseDto> batchUpdateCourses(List<CourseDto> CourseDtoList);
    public List<CourseProjection> getAllCourse();
}
