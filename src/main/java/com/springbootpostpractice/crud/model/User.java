package com.springbootpostpractice.crud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String name;
    private String userType;
    private String password;

}
