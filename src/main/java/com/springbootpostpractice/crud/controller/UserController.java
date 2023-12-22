package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.LoginResponse;
import com.springbootpostpractice.crud.dto.UserLoginRequest;
import com.springbootpostpractice.crud.model.User;
import com.springbootpostpractice.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/userRegister")
    public User registerUser(@RequestBody User user)
    {
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.loginUser(userLoginRequest.getUsername(), userLoginRequest.getPassword());
    }
}
