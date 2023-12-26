package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.dto.*;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.model.User;
import com.springbootpostpractice.crud.repository.GuardianRepository;
import com.springbootpostpractice.crud.repository.Projection.GuardianProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import com.springbootpostpractice.crud.repository.UserRepository;
import com.springbootpostpractice.crud.service.GuardianService;
import com.springbootpostpractice.crud.service.StudentService;
import com.springbootpostpractice.crud.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


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
    public StudentCourseDtoByGuardian getStudentInfoByGuardianEmail(String guardianEmail) {
        Guardian guardian = guardianRepository.findByEmail(guardianEmail)
                .orElseThrow(() -> new NoSuchElementException("Guardian not found"));

        Student student = guardian.getStudent();
        if (student == null) {
            throw new NoSuchElementException("Student not found for guardian");
        }

        List<String> enrolledCourses = student.getCourseEnrollments()
                .stream()
                .map(courseEnrollment -> courseEnrollment.getCourse().getName())
                .collect(Collectors.toList());

        return new StudentCourseDtoByGuardian(
                student.getId(),
                student.getName(),
                student.getRollno(),
                student.getEmail(),
                student.getAge(),
                enrolledCourses
        );
    }
    @Override
    @Transactional
    public ResponseEntity<String> saveGuardian(SaveGuardianDTO guardianDTO) {
        try {
            Student student = studentService.getStudentByRollNo(guardianDTO.getStudentRollno());

            if (student.getGuardian() != null) {
                return new ResponseEntity<>("Guardian already exists for the given student.", HttpStatus.BAD_REQUEST);
            }

            if (userService.existsByUsername(guardianDTO.getUsername())) {
                return new ResponseEntity<>("A guardian with the given username already exists.", HttpStatus.BAD_REQUEST);
            }

            Guardian guardian = new Guardian();
            guardian.setName(guardianDTO.getName());
            guardian.setEmail(guardianDTO.getEmail());
            guardian.setPhoneNo(guardianDTO.getPhoneNo());
            guardian.setUsername(guardianDTO.getUsername());
            guardian.setStudent(student);

            guardianRepository.save(guardian);

            User user = new User();
            user.setUsername(guardianDTO.getUsername());
            user.setEmail(guardianDTO.getEmail());
            user.setName(guardianDTO.getName());
            user.setUserType("guardian");
            user.setPassword(guardianDTO.getUsername());

            userRepository.save(user);

            return new ResponseEntity<>("Guardian and associated User saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public GuadDto getGuardianProfile(String username) {
        Optional<Guardian> guardianOptional = guardianRepository.findByUsername(username);
        if (guardianOptional.isPresent()) {
            return convertToDto(guardianOptional.get());
        } else {
            return null;
        }
    }

    private GuadDto convertToDto(Guardian guardian) {
        GuadDto dto = new GuadDto();
        // Map fields from Guardian to GuardianDto
        dto.setName(guardian.getName());
        dto.setUsername(guardian.getUsername());
        dto.setEmail(guardian.getEmail());
        dto.setPhoneNo(guardian.getPhoneNo());
        return dto;
    }
}





