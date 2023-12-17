package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.CourseEnrolledDto;
import com.springbootpostpractice.crud.dto.StudentNameRollno;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface CourseEnrolledService
{
    public String saveCourseEnroll(CourseEnrolledDto courseEnrolledDto);
    List<StudentNameRollno> getStudentsByCourseId(Integer courseId);

}
