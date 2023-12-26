package com.springbootpostpractice.crud.controller;

import com.springbootpostpractice.crud.dto.TeacherDTO;
import com.springbootpostpractice.crud.model.Teacher;
import com.springbootpostpractice.crud.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TeacherController {
    //registration of teacher happens in user registration

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacher-pagination")
    public Page<Teacher> getTeacherDetailPagination(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortType,
            @RequestParam(required = false) String search
    ) {
        Sort.Direction direction = sortType.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return teacherService.getTeacherDetailPagination(pageable, search);
    }


    @PutMapping("/update-teacher/{username}")
    public String updateTeacher(@PathVariable String username, @RequestBody TeacherDTO teacherDTO) {
       return teacherService.updateTeacher(username, teacherDTO);
    }

    @GetMapping("/teacher-profile/{username}")
    public ResponseEntity<Teacher> getTeacherByUsername(@PathVariable String username) {
        try {
            Teacher teacher = teacherService.getTeacherByUsername(username);

            if (teacher != null) {
                return new ResponseEntity<>(teacher, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
