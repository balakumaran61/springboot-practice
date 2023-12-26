package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.dto.LoginResponse;
import com.springbootpostpractice.crud.dto.UserRequest;
import com.springbootpostpractice.crud.model.Teacher;
import com.springbootpostpractice.crud.model.User;
import com.springbootpostpractice.crud.repository.TeacherRepository;
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
    private TeacherRepository teacherRepository;

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

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(userRequest, teacher);
        teacherRepository.save(teacher);

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
    @Override
    public boolean existsByUsername(String username) {
        // Implement the logic to check if a user with the given username exists
        // This might involve querying your UserRepository
        return userRepository.existsByUsername(username);
    }

}
