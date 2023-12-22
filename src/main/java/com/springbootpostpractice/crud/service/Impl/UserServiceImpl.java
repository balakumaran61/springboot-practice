package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.dto.LoginResponse;
import com.springbootpostpractice.crud.dto.UserRequest;
import com.springbootpostpractice.crud.model.User;
import com.springbootpostpractice.crud.repository.UserRepository;
import com.springbootpostpractice.crud.service.UserService;
import com.springbootpostpractice.crud.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserRequest registerUser(UserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        userRepository.save(user);

        return userRequest;
    }

    @Override
    public LoginResponse loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            String token = jwtTokenUtil.generateToken(user);
            LoginResponse response = new LoginResponse();
            response.setUsername(username);
            response.setUserType(user.getUserType());
            response.setToken(token);
            return response;
        }

        LoginResponse failureResponse = new LoginResponse();
        failureResponse.setUsername("Login failed");
        failureResponse.setUserType("error");
        return failureResponse;
    }

}
