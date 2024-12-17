package com.school.school_management.repository;

import com.school.school_management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findById(int id);
}
