package com.springbootpostpractice.crud.dto;
import lombok.Data;

@Data
public class GuardianDto {
    private String name;
    private String email;
    private String phoneNo;
    private Integer studentId;
}
