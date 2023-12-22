package com.springbootpostpractice.crud.dto;

import lombok.Data;

@Data
public class StudentRequest {
    private String name;
    private String email;
    private String rollno; // This will be used as username for User entity
    private int age;

}
