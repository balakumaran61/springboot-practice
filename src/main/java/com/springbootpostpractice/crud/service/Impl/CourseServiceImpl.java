package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.controller.CourseController;
import com.springbootpostpractice.crud.dto.CourseDto;
import com.springbootpostpractice.crud.model.Course;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.CourseRepository;
import com.springbootpostpractice.crud.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService
{
    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseDto saveCourse(Course course)
    {   Course savedCourse = courseRepository.save(course);
        return convertToCourseDto(savedCourse);
    }

    private CourseDto convertToCourseDto(Course course)
    {
        CourseDto couDto= new CourseDto();
        couDto.setCourseCode(course.getCourseCode());
        couDto.setName(course.getName());
        couDto.setFees(course.getFees());
        return couDto;


    }
    @Override
    public Course getCourseById(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }


}
