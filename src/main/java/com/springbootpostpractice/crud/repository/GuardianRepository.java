package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.dto.StudentDetail;
import com.springbootpostpractice.crud.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GuardianRepository  extends JpaRepository<Guardian, Integer> {
    @Query("SELECT new com.springbootpostpractice.crud.dto.StudentDetail(g.student.name, g.student.email) FROM Guardian g WHERE g.id = ?1")
    StudentDetail findStudentDetailsByGuardianId(Integer guardianId);
    }
