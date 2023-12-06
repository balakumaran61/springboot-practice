package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.model.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course,Integer> {
    Course findByCourseCode(String courseCode);
}
