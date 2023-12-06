package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.CourseEnrolledDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface CourseEnrolledService
{
    public String saveCourseEnroll(CourseEnrolledDto courseEnrolledDto);
}
