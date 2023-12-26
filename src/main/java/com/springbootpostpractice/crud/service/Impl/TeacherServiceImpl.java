package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.dto.TeacherDTO;
import com.springbootpostpractice.crud.model.Teacher;
import com.springbootpostpractice.crud.model.User;
import com.springbootpostpractice.crud.repository.TeacherRepository;
import com.springbootpostpractice.crud.repository.UserRepository;
import com.springbootpostpractice.crud.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Teacher> getTeacherDetailPagination(Pageable pageable, String search) {
        return teacherRepository.findAllTeachers(pageable, search);
    }


    @Override
    public String updateTeacher(String username, TeacherDTO teacherDTO) {
        Teacher existingTeacher = teacherRepository.findByUsername(username);

        if (existingTeacher != null) {
            // Check if the email is unique before updating
            Teacher teacherWithSameEmail = teacherRepository.findByEmail(teacherDTO.getEmail());
            if (teacherWithSameEmail != null && !teacherWithSameEmail.getUsername().equals(username)) {
                return "Error: Email is already associated with another teacher.";
            }

            BeanUtils.copyProperties(teacherDTO, existingTeacher, "id", "username");

            teacherRepository.save(existingTeacher);

            User user = userRepository.findByUsername(existingTeacher.getUsername());
            if (user != null) {
                user.setName(existingTeacher.getName());
                user.setEmail(existingTeacher.getEmail());
                userRepository.save(user);
            }

            return "Teacher updated successfully.";
        }

        return "Error: Teacher with the given username not found.";
    }

    @Override
    public Teacher getTeacherByUsername(String username) {
        // Call the repository method to find a teacher by username
        return teacherRepository.findByUsername(username);
    }
}
