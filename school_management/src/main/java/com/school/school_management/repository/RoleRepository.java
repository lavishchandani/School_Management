package com.school.school_management.repository;

import java.util.Optional;

import com.school.school_management.model.ERole;
import com.school.school_management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
