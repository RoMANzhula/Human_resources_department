package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByUsername(String username);

    User findByActivationCode(String activationCode);

    User findBySecretCodeWithRegistration(String secretCode);

    @Query("SELECT u FROM User u WHERE :role MEMBER OF u.userRoles")
    List<User> findByUserRoles(@Param("role") Role role);
}
