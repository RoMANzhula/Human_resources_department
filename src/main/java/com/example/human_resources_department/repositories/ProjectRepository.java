package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project getProjectById(Long projectId);
}
