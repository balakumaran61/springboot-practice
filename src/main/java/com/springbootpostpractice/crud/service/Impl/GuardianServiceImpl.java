package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.GuardianRepository;
import com.springbootpostpractice.crud.service.GuardianService;
import com.springbootpostpractice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.springbootpostpractice.crud.dto.GuardianDto;

import com.springbootpostpractice.crud.dto.studentDto;

@Service
public class GuardianServiceImpl implements GuardianService
{

    @Autowired
    GuardianRepository guardianRepository;

    @Autowired
    StudentService studentService;
    @Override
    public Guardian createGuardian(GuardianDto guardianDto) {
        Guardian guardianToCreate = new Guardian();
        guardianToCreate.setName(guardianDto.getName());
        guardianToCreate.setEmail(guardianDto.getEmail());
        guardianToCreate.setPhoneNo(guardianDto.getPhoneNo());
        // Fetch the corresponding Student entity from the database

        Student student = studentService.getStudentById(guardianDto.getStudentId());
        guardianToCreate.setStudentId(student.getId());
        return guardianRepository.save(guardianToCreate);

    }
}
