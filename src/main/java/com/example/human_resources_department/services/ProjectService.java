package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }
}
