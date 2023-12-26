package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.dto.StudentDetail;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.repository.Projection.GuardianProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GuardianRepository  extends JpaRepository<Guardian, Integer> {
    @Query("SELECT new com.springbootpostpractice.crud.dto.StudentDetail(g.student.name, g.student.email) FROM Guardian g WHERE g.id = ?1")
    StudentDetail findStudentDetailsByGuardianId(Integer guardianId);

    @Query(value = "select e from Guardian e where " +
            "lower(e.name) like %:search% or " +
            "lower(e.email) like %:search% or " +
            "lower(e.phoneNo) like %:search%")
    Page<GuardianProjection> findAllGuardians(Pageable pageable, @Param("search") String search);
    Optional<Guardian> findByEmail(String email);
    Optional<Guardian> findByUsername(String username);


}
