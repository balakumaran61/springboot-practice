package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

    public interface StudentRepository extends JpaRepository<Student, Integer>
    {
        List<StudentProjection> findByAgeGreaterThan(int age);
        @Query(value = "SELECT name, rollno, age FROM student WHERE age < :age", nativeQuery = true)
        List<StudentProjection> findStudentsWithAgeLessThan(@Param("age") int age);

        @Query(value = "SELECT name, rollno, age FROM student WHERE age = :age", nativeQuery = true)
        List<Object[]> findStudentsByAge(@Param("age") int age);

        // Native SQL query to fetch details of students based on guardian ID
        @Query(value = "SELECT s.* FROM student s " +
                "JOIN guardian g ON s.id = g.student_id " +
                "WHERE g.id = :guardianId", nativeQuery = true)
        Student findStudentsByGuardianId(@Param("guardianId") Integer guardianId);

        @Modifying
        @Query(value = "UPDATE student SET email = :email WHERE id = :studentId", nativeQuery = true)
        int updateStudentEmail(@Param("studentId") Integer studentId, @Param("email") String email);




    }
