package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project getProjectById(Long projectId);

    List<Project> findAllByCoworkersId(Long currentUserId);
}
