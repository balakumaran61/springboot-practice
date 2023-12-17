package com.springbootpostpractice.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetail {

    private String name;
    private String email;


    // Getters and setters
}