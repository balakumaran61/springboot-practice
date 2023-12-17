package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.GuardianDto;
import com.springbootpostpractice.crud.dto.StudentDetail;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.service.GuardianService;
import com.springbootpostpractice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
