    package com.springbootpostpractice.crud.model;
    
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;
    
    import java.util.*;
    
    @Entity
    @Getter
    @Setter
    public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
    
        private String name;
        private String email;
        private String rollno;
        private int age;
    
        @OneToOne(mappedBy = "student")
        private Guardian guardian;
        @OneToMany(mappedBy = "student")
        private List<CourseEnrolled> courseEnrollments;

        // Other fields and methods
    }