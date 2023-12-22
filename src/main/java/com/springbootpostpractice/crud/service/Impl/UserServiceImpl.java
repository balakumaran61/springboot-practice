package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.dto.LoginResponse;
import com.springbootpostpractice.crud.model.User;
import com.springbootpostpractice.crud.repository.UserRepository;
import com.springbootpostpractice.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("username already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public LoginResponse loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Create and return the LoginResponse DTO for successful login
            LoginResponse response = new LoginResponse();
            response.setUsername(username);
            response.setUserType(user.getUserType());
            return response;
        }

        // Create and return a LoginResponse DTO for login failure
        LoginResponse failureResponse = new LoginResponse();
        failureResponse.setUsername("Login failed");
        failureResponse.setUserType("error");
        return failureResponse;
    }

}
