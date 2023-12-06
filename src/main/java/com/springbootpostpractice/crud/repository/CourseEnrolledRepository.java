package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.model.CourseEnrolled;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEnrolledRepository extends JpaRepository<CourseEnrolled, Integer> {
}
