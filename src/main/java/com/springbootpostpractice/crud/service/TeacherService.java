package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.TeacherDTO;
import com.springbootpostpractice.crud.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    Page<Teacher> getTeacherDetailPagination(Pageable pageable, String search);
    String updateTeacher(String username, TeacherDTO teacherDTO);
    Teacher getTeacherByUsername(String username);
}
