package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.LoginResponse;
import com.springbootpostpractice.crud.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(User user);
    LoginResponse loginUser(String username, String password);

}
