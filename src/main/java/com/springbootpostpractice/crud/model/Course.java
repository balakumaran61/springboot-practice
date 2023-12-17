package com.springbootpostpractice.crud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String courseCode; // Change to courseCode to match JSON input
    private String name;
    private int fees;
    @OneToMany(mappedBy = "course")
    private List<CourseEnrolled> courseEnrollments;
}
