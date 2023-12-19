package com.springbootpostpractice.crud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentWithCoursesDTO {
    private Integer id;
    private String name;
    private String email;
    private String rollno;
    private int age;

    // List of CourseEnrolledDTO or relevant details
    private List<CourseEnrolledDto> coursesEnrolled;
}
