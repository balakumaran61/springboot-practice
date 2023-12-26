package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository  extends JpaRepository<Teacher , Long> {
    Teacher findByUsername(String username);
    Teacher findByEmail(String email);

    @Query(value = "select t from Teacher t where " +
            "lower(t.name) like %:search% or " +
            "lower(t.email) like %:search% or " +
            "lower(t.phoneNo) like %:search%")
    Page<Teacher> findAllTeachers(Pageable pageable, @Param("search") String search);
}
