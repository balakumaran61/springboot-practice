package com.springbootpostpractice.crud.service.Impl;
import com.springbootpostpractice.crud.dto.StudentUpdateDto;
import com.springbootpostpractice.crud.dto.studentDto;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import com.springbootpostpractice.crud.repository.StudentRepository;
import com.springbootpostpractice.crud.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService
{
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student create(Student student)
    {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
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
    public String updateStudentEmail(Integer studentId, String email) {
        int updatedRows = studentRepository.updateStudentEmail(studentId, email);
        if (updatedRows > 0) {
            return "email updated";
        } else {
            return "email not updated";
        }
    }
}
