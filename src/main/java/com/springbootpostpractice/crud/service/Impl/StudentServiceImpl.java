package com.springbootpostpractice.crud.service.Impl;
import com.springbootpostpractice.crud.dto.*;
import com.springbootpostpractice.crud.model.CourseEnrolled;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.model.User;
import com.springbootpostpractice.crud.repository.Projection.StudentPageProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import com.springbootpostpractice.crud.repository.StudentRepository;
import com.springbootpostpractice.crud.repository.UserRepository;
import com.springbootpostpractice.crud.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService
{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Student create(Student student)
    {
        return studentRepository.save(student);
    }

    @Override
    public Page<StudentPageProjection> getStudentPage(Pageable pageble) {
        return null;
    }

    @Override
    public List<StudentProjection> getAllStudents()
    {
        return studentRepository.findAllProjectedBy();
    }

    @Override
    public Student getOneStudent(Integer id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public studentDto getStudentDetailsById(Integer id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentDto details = new studentDto();
                    details.setName(student.getName());
                    details.setRollno(student.getRollno());
                    return details;
                })
                .orElse(null);
    }
    @Override
    public List<studentDto> getStudentByAgeGreaterThan(int age) {
        List<StudentProjection> students = studentRepository.findByAgeGreaterThan(age);
        return students.stream()
                .map(student -> {
                    studentDto detail = new studentDto();
                    detail.setName(student.getName());
                    detail.setRollno(student.getRollno());
                    detail.setAge(student.getAge());
                    return detail;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentProjection> getStudentsWithAgeLessThan(int age) {
        return studentRepository.findStudentsWithAgeLessThan(age);
    }
    @Override
    public List<studentDto> getStudentsByAge(int age) {
        List<Object[]> results = studentRepository.findStudentsByAge(age);

        return results.stream()
                .map(result -> {
                    studentDto dto = new studentDto();
                    dto.setName((String) result[0]);
                    dto.setRollno((String) result[1]);
                    dto.setAge((int) result[2]);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Override
    public Student getStudentById(Integer studentId)
    {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public studentDto getStudentsByGuardianId(Integer guardianId) {
        Student student = studentRepository.findStudentsByGuardianId(guardianId);
        return covertStudentToStudentDto(student);
    }

    private studentDto covertStudentToStudentDto(Student student)
    {
        if(student==null)
        {
            return null;

        }
        studentDto studDto = new studentDto();
        studDto.setName(student.getName());
        studDto.setEmail(student.getEmail());
        studDto.setRollno(student.getRollno());
        studDto.setAge(student.getAge());


        return studDto;
    }
    @Override
    public studentDto updateStudent(Integer studentId, StudentUpdateDto studentUpdateDto)
    {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));
        existingStudent.setEmail(studentUpdateDto.getEmail());
        Student updatedStudent = studentRepository.save(existingStudent);
        return covertStudentToStudentDto(updatedStudent);


    }
    @Override
    @Transactional
    public String updateStudentEmail(Integer studentId, String email)
    {
        int updatedRows = studentRepository.updateStudentEmail(studentId, email);
        if (updatedRows > 0) {
            return "email updated";
        } else {
            return "email not updated";
        }
    }
    @Override
    public List<studentDto> batchUpdateStudents(List<studentDto> studentDtoList) {
        List<studentDto> updatedStudentDtos = new ArrayList<>();

        for (studentDto inputStudentDto : studentDtoList) {
            Optional<Student> existingStudentOptional = studentRepository.findById(inputStudentDto.getStudentId());

            if (existingStudentOptional.isPresent()) {
                // Student exists in the database, update its fields
                Student existingStudent = existingStudentOptional.get();
                updateEntityFromDto(existingStudent, inputStudentDto);
                studentRepository.save(existingStudent);
                updatedStudentDtos.add(convertEntityToDto(existingStudent));
            } else {
                // Student does not exist in the database, create a new entity
                Student newStudent = convertDtoToEntity(inputStudentDto);
                studentRepository.save(newStudent);
                updatedStudentDtos.add(convertEntityToDto(newStudent));
            }
        }

        return updatedStudentDtos;
    }


    private void updateEntityFromDto(Student student, studentDto studentDto) {
        // Update fields of the existing entity with data from the DTO
        student.setName(studentDto.getName());
        student.setRollno(studentDto.getRollno());
        student.setEmail(studentDto.getEmail());
        student.setAge(studentDto.getAge());
        // You can update other fields as needed
    }

    private Student convertDtoToEntity(studentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        return student;
    }

    private studentDto convertEntityToDto(Student student) {
        studentDto studDto = new studentDto();
        BeanUtils.copyProperties(student, studDto);
        return studDto;
    }




    @Transactional
    @Override
    public String saveStudentUsingEm(Student student) {
        entityManager.persist(student);
        return "Student saved successfully";
    }
    @Override
    public studDto getStudentDetailsWithEM(Integer studentId)
    {
        String queryString = "SELECT name, rollno, email, age FROM Student WHERE id = :studentId";
        Query nativeQuery = entityManager.createNativeQuery(queryString);
        nativeQuery.setParameter("studentId", studentId);

        Object[] result = (Object[]) nativeQuery.getSingleResult();

        if (result != null && result.length == 4) {
            // Assuming the order is name, rollno, email, age
            String name = (String) result[0];
            String rollno = (String) result[1];
            String email = (String) result[2];
            int age = (int) result[3];

            return new studDto(name, rollno, email, age);
        } else {
            // Handle the case where the result is not as expected
            // You might want to throw an exception or return a default value
            return null;
        }
    }

    @Override
    public void updateStudent(String rollno, studDto updatedStudentDto) {
        // Find the student by rollno
        Optional<Student> optionalStudent = studentRepository.findByRollno(rollno);

        if (optionalStudent.isPresent()) {
            // Update attributes based on studDto
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(updatedStudentDto.getName());
            existingStudent.setEmail(updatedStudentDto.getEmail());
            existingStudent.setAge(updatedStudentDto.getAge());

            // Save the updated student
            studentRepository.save(existingStudent);
        } else {

            // Handle case where student with the given rollno is not found
            // You may throw an exception or handle it according to your application's requirements
        }
    }

    @Override
    public StudentProjection getStudentNameDetail(String rollno) {
        return studentRepository.findByRoll(rollno);
    }
    @Override
    public Page<StudentProjection> getStudentDetailPagination(String searchString, Pageable pageable) {
        return studentRepository.findAllStudents(searchString, pageable);
    }

    @Override
    public void saveStudentAndUser(StudentRequest studentRequest) {
        // Create Student entity
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setRollno(studentRequest.getRollno());
        student.setAge(studentRequest.getAge());

        // Create User entity
        User user = new User();
        user.setUsername(studentRequest.getRollno()); // Using rollno as username
        user.setEmail(studentRequest.getEmail());
        user.setName(studentRequest.getName());
        user.setUserType("student"); // Hardcoded userType
        user.setPassword(studentRequest.getRollno()); // Set a default password



        // Save entities
        studentRepository.save(student);
        userRepository.save(user);
    }

    public boolean isRollnoExists(String rollno) {
        return studentRepository.existsByRollno(rollno);
    }

    @Override
    public Student getStudentByRollNo(String rollNo) {
        // Use the existing repository method to find a student by roll number
        return studentRepository.findByRollno(rollNo)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with roll number: " + rollNo));
    }

    @Override
    public studentDto getOneStudentDetail(String rollno) {
        Student student = studentRepository.findStudentByRollno(rollno);
        if (student != null) {
            return convertToDto(student);
        } else {
            return null;
        }
    }

    private studentDto convertToDto(Student student) {
        studentDto studenDto = new studentDto();
        studenDto.setName(student.getName());
        studenDto.setRollno(student.getRollno());
        studenDto.setEmail(student.getEmail());
        studenDto.setAge(student.getAge());
        // Set other properties as needed
        return studenDto;
    }

}





