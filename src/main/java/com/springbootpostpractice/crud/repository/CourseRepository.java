package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.model.Course;
import com.springbootpostpractice.crud.repository.Projection.CourseProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository  extends JpaRepository<Course,Integer> {
    Course findByCourseCode(String courseCode);

    //   @Query(value = "SELECT name, rollno, age FROM student WHERE age = :age", nativeQuery = true)
    List<CourseProjection> findAllProjectedBy();

}
