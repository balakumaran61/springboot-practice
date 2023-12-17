package com.springbootpostpractice.crud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseEnrolled {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Other fields and methods
}