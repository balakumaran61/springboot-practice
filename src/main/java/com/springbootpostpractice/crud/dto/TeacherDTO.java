package com.springbootpostpractice.crud.dto;

import lombok.Data;
@Data
public class TeacherDTO {
    private String email;
    private String name;
    private String gender;
    private int age;
    private String phoneNo;
}