package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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


    public void createTeam(Long projectId, Map<Role, Integer> rolesAndCounts) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        project.getRolesAndCounts().clear();

        // Додайте нові ролі та їх кількість
        project.getRolesAndCounts().putAll(rolesAndCounts);

        projectRepository.save(project);
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public Map<Role, Integer> getAllRoles() {
        Role[] allRoles = Role.values();

        Map<Role, Integer> roles = new HashMap<>();
        Arrays.stream(allRoles).forEach(role -> roles.put(role, 0));

        return roles;
    }
}
