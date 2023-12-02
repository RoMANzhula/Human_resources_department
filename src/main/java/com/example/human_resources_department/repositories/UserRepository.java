package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByUsername(String username);

    User findByActivationCode(String activationCode);

    User findBySecretCodeWithRegistration(String secretCode);

    @Query("SELECT u FROM User u WHERE :role MEMBER OF u.userRoles")
    List<User> findByUserRoles(@Param("role") Role role);

    @Query("SELECT u FROM User u JOIN u.projects p WHERE p.id = :projectId")
    List<User> findTeamMembersByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN u.projects p " +
            "WHERE p.id IN :projectIds")
    List<User> findCoworkersByProjectIds(@Param("projectIds") List<Long> projectIds);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN u.projects p " +
            "WHERE p IN :projects")
    List<User> findCoworkersByProjects(@Param("projects") List<Project> projects);

}
