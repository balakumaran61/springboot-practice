package com.springbootpostpractice.crud.repository;

import com.springbootpostpractice.crud.dto.StudentDetail;
import com.springbootpostpractice.crud.dto.studDto;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.Projection.StudentPageProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Integer>
    {
        List<StudentProjection> findAllProjectedBy();
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

        @Query(value ="select e from Student e where e.age = :age ORDER BY e.name")
        Page<StudentPageProjection> findAllStudentsByAge(
                @Param("age") int age,
                Pageable pageable
                );


        Optional<Student> findByRollno(String rollno);

        @Query(value ="SELECT e from Student e WHERE e.rollno = :rollno")
        StudentProjection findByRoll(@Param("rollno") String rollno);

        @Query("SELECT e FROM Student e " +
                "WHERE " +
                "   (:searchString IS NULL OR " +
                "       e.name LIKE %:searchString% OR " +
                "       e.email LIKE %:searchString% OR " +
                "       e.rollno LIKE %:searchString%)")
        Page<StudentProjection> findAllStudents(@Param("searchString") String searchString, Pageable pageable);

        boolean existsByRollno(String rollno);

        Student findStudentByRollno(String rollno);




    }
