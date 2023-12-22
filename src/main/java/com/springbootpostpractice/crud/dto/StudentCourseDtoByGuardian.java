package com.springbootpostpractice.crud.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDtoByGuardian {
    private Integer studentId;
    private String name;
    private String rollno;
    private String email;
    private int age;
    private List<String> enrolledCourses;

    // Constructors, getters, and setters
    // ...


}
