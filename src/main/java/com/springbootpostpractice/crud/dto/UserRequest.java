package com.springbootpostpractice.crud.dto;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String name;
    private String userType;
    private String password;

}
