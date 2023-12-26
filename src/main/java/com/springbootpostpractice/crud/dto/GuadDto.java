package com.springbootpostpractice.crud.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class GuadDto {
    private String name;

    private String username;
    private String email;
    private String phoneNo;
}
