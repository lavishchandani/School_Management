package com.school.school_management.controller;

import com.school.school_management.dto.CourseDto;
import com.school.school_management.model.Course;
import com.school.school_management.model.Teacher;
import com.school.school_management.repository.CourseRepository;
import com.school.school_management.repository.TeacherRepository;
import com.school.school_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    private CourseService courseService;



    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseRepository.findAll().stream()
                .map(course -> new CourseDto(
                        course.getId(),
                        course.getName(),
                        course.getDescription(),
                        course.getTeacher() != null ? course.getTeacher().getId() : null
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course, Authentication authentication) {
        String username = authentication.getName();



        Course savedCourse = courseRepository.save(course);

        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(id, courseDetails);

        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);

        return ResponseEntity.ok("Course deleted successfully.");
    }
}
