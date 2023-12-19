package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.dto.*;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.GuardianRepository;
import com.springbootpostpractice.crud.repository.Projection.GuardianProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import com.springbootpostpractice.crud.service.GuardianService;
import com.springbootpostpractice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
        guardianToCreate.setStudent(student);
        return guardianRepository.save(guardianToCreate);

    }
    @Override
    public StudentDetail getStudentDetailsByGuardianId(Integer guardianId) {
        return guardianRepository.findStudentDetailsByGuardianId(guardianId);
    }

    @Override
    public Page<GuardianProjection> getGuardianDetailPagination(Pageable pageable, String search) {
        return guardianRepository.findAllGuardians(pageable, search);
    }

    @Override
    public void updateGuardian(String email, guardianUpdateDto updatedGuardian) {
        Optional<Guardian> optionalGuardian = guardianRepository.findByEmail(email);

        if (optionalGuardian.isPresent()) {
            // Update attributes based on studDto
            Guardian existinGuardian = optionalGuardian.get();
            existinGuardian.setName(updatedGuardian.getName());

            existinGuardian.setPhoneNo(updatedGuardian.getPhoneNo());

            // Save the updated student
            guardianRepository.save(existinGuardian);
        } else {

            // Handle case where student with the given rollno is not found
            // You may throw an exception or handle it according to your application's requirements
        }
    }

    // Assuming you have a method to find a guardian by email in your repository

        @Override
        public studentDto getStudentInfoByGuardianEmail(String guardianEmail) {
            Guardian guardian = guardianRepository.findByEmail(guardianEmail)
                    .orElseThrow(() -> new NoSuchElementException("Guardian not found"));

            Student student = guardian.getStudent();
            if (student == null) {
                throw new NoSuchElementException("Student not found for guardian");
            }

            studentDto studentDto = new studentDto();
            studentDto.setStudentId(student.getId());
            studentDto.setName(student.getName());
            studentDto.setRollno(student.getRollno());
            studentDto.setEmail(student.getEmail());
            studentDto.setAge(student.getAge());

            return studentDto;
        }
}
