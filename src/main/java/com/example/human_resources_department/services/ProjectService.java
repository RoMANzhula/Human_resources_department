package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final LocalFileStorageService localFileStorageService;

    public ProjectService(
            ProjectRepository projectRepository,
            LocalFileStorageService localFileStorageService
    ) {
        this.projectRepository = projectRepository;
        this.localFileStorageService = localFileStorageService;
    }

    @Transactional(readOnly = true)
    public Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    @Transactional
    public void saveProject(Project project, MultipartFile fileContractName) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }

        if (project.getDeadline().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Deadline must be in the future");
        }

        project.setDateOfRegistration(new Date());

        if (fileContractName != null && !fileContractName.getOriginalFilename().isEmpty()) {
            String uniqueFileName = localFileStorageService.storeFile(fileContractName);
            project.setFileContractName(uniqueFileName);
        }

        projectRepository.save(project);

    }

}
