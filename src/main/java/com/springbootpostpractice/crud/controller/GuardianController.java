package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.*;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.Projection.GuardianProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import com.springbootpostpractice.crud.service.GuardianService;
import com.springbootpostpractice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GuardianController {
    @Autowired
    GuardianService guardianService;

    @PostMapping("/guardian")
    public ResponseEntity<String> createGuardian(@RequestBody GuardianDto guardianDTO) {
        Guardian createdGuardian = guardianService.createGuardian(guardianDTO);
        return ResponseEntity.ok("Guardian created successfully");
    }
    @GetMapping("/{guardianId}/student-details")
    public StudentDetail getStudentDetailsByGuardian(@PathVariable Integer guardianId) {
        return guardianService.getStudentDetailsByGuardianId(guardianId);
    }
    @GetMapping("/guardian-pagination")
    public Page<GuardianProjection> getGuardianDetailPagination(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortType,
            @RequestParam(required = false) String search
    ) {
        Sort.Direction direction = sortType.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return guardianService.getGuardianDetailPagination(pageable, search);
    }


     @GetMapping("/studentInfoByGuardianEmail/{guardianEmail}")
    public ResponseEntity<StudentCourseDtoByGuardian> getStudentInfo(@PathVariable String guardianEmail) {
        try {
            StudentCourseDtoByGuardian studentInfo = guardianService.getStudentInfoByGuardianEmail(guardianEmail);
            return ResponseEntity.ok(studentInfo);
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/updateGuardian/{email}")
    public   void updateGuardian(@PathVariable String email,@RequestBody guardianUpdateDto updatedGuardian)
    {
        guardianService.updateGuardian(email,updatedGuardian);
    }

            /*
             void updateGuardian( String email,guardianUpdateDto updatedGuardian);
             */
}
