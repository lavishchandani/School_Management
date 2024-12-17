package com.school.school_management.repository;

import com.school.school_management.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Custom query methods can be added if required
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByUserUsername(String username);
}
