package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.model.CourseEnrolled;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseEnrolledRepository extends JpaRepository<CourseEnrolled, Integer> {
    List<CourseEnrolled> findByCourse_Id(Integer courseId);
}
