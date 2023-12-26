package com.springbootpostpractice.crud.dto;

import lombok.Data;

@Data
public class SaveGuardianDTO {
    private String name;
    private String email;
    private String phoneNo;
    private String studentRollno;
    private String username;
}
