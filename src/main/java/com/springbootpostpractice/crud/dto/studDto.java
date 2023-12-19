package com.springbootpostpractice.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class studDto
{
    private String name;
    private String rollno;
    private String email;
    private int age;
    public void updateAttributes(studDto updatedStudent) {
        // Update attributes based on the provided studDto
        this.name = updatedStudent.getName();
        this.email = updatedStudent.getEmail();
        this.age = updatedStudent.getAge();
        // You can also update other attributes if needed
    }
}
