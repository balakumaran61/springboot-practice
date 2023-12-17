package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.dto.CourseEnrolledDto;
import com.springbootpostpractice.crud.dto.StudentNameRollno;
import com.springbootpostpractice.crud.model.Course;
import com.springbootpostpractice.crud.model.CourseEnrolled;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.CourseEnrolledRepository;
import com.springbootpostpractice.crud.repository.CourseRepository;
import com.springbootpostpractice.crud.repository.GuardianRepository;
import com.springbootpostpractice.crud.service.CourseEnrolledService;
import com.springbootpostpractice.crud.service.CourseService;
import com.springbootpostpractice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseEnrolledServiceImpl implements CourseEnrolledService
{

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseEnrolledRepository courseEnrolledRepository;


    @Override
    public String saveCourseEnroll(CourseEnrolledDto courseEnrolledDto)
    {
        CourseEnrolled courseEnrollCreate = new CourseEnrolled();

        Student student = studentService.getStudentById(courseEnrolledDto.getStudentId());
        Course course= courseService.getCourseById(courseEnrolledDto.getCourseCode());

        courseEnrollCreate.setStudent(student);

        courseEnrollCreate.setCourse(course);

        courseEnrolledRepository.save(courseEnrollCreate);
        return ("course enrolled successfully");


    }

    @Override
    public List<StudentNameRollno> getStudentsByCourseId(Integer courseId) {
        List<CourseEnrolled> enrollments = courseEnrolledRepository.findByCourse_Id(courseId);

        return enrollments.stream()
                .map(enrollment -> new StudentNameRollno(enrollment.getStudent().getName(), enrollment.getStudent().getRollno())).collect(Collectors.toList());
    }




}
