package com.springbootpostpractice.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class studentDto
{
    private Integer studentId;
    private String name;
    private String rollno;
    private String email;
    private int age;
}
