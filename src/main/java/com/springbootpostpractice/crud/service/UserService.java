package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.LoginResponse;
import com.springbootpostpractice.crud.dto.UserRequest;
import com.springbootpostpractice.crud.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserRequest registerUser(UserRequest userRequest);
    LoginResponse loginUser(String username, String password);

}
