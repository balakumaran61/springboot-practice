package com.springbootpostpractice.crud.service.Impl;

import com.springbootpostpractice.crud.controller.CourseController;
import com.springbootpostpractice.crud.dto.CourseDto;
import com.springbootpostpractice.crud.dto.studentDto;
import com.springbootpostpractice.crud.model.Course;
import com.springbootpostpractice.crud.model.Student;
import com.springbootpostpractice.crud.repository.CourseRepository;
import com.springbootpostpractice.crud.repository.Projection.CourseProjection;
import com.springbootpostpractice.crud.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService
{
    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseDto saveCourse(Course course)
    {   Course savedCourse = courseRepository.save(course);
        return convertToCourseDto(savedCourse);
    }

    private CourseDto convertToCourseDto(Course course)
    {
        CourseDto couDto= new CourseDto();
        couDto.setCourseCode(course.getCourseCode());
        couDto.setName(course.getName());
        couDto.setFees(course.getFees());
        return couDto;


    }
    @Override
    public Course getCourseById(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }

    public List<CourseDto> batchUpdateCourses(List<CourseDto> CourseDtoList)
    {
        List<CourseDto> updatedCoursesDtos = new ArrayList<>();
        for (CourseDto inputCourseDto : CourseDtoList)
        {
            Optional<Course> existingCourseOptional =courseRepository.findById(inputCourseDto.getCourseId());
            if (existingCourseOptional.isPresent())
            {
                Course existingCourse=existingCourseOptional.get();
                updateEntityFromDto(existingCourse, inputCourseDto);
                courseRepository.save(existingCourse);
                updatedCoursesDtos.add(convertEntityToDto(existingCourse));

            }
            else
            {
                Course newCourse = convertDtoToEntity(inputCourseDto);
                courseRepository.save(newCourse);
                updatedCoursesDtos.add(convertEntityToDto(newCourse));
            }

        }
        return updatedCoursesDtos;

    }



    private void updateEntityFromDto(Course course,CourseDto courseDto)
    {
        course.setName(courseDto.getName());
        course.setCourseCode(courseDto.getCourseCode());
        course.setFees(courseDto.getFees());
    }
    private CourseDto convertEntityToDto(Course course)
    {
        CourseDto couDto = new CourseDto();
        BeanUtils.copyProperties(course, couDto);
        return couDto;
    }
    private Course convertDtoToEntity(CourseDto courseDto)
    {
        Course course= new Course();
        BeanUtils.copyProperties(courseDto,course );
        return course;
    }

    public List<CourseProjection> getAllCourse()
    {
        return courseRepository.findAllProjectedBy();
    }

}
