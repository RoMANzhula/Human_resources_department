package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.ProjectRepository;
import com.example.human_resources_department.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final LocalFileStorageService localFileStorageService;
    private final UserRepository userRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            LocalFileStorageService localFileStorageService,
            UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.localFileStorageService = localFileStorageService;
        this.userRepository = userRepository;
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

    @Transactional
    public void createTeam(Long projectId, Map<Role, Integer> rolesAndCounts) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        project.getRolesAndCounts().clear();

        // Додайте нові ролі та їх кількість
        project.getRolesAndCounts().putAll(rolesAndCounts);

        projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    @Transactional(readOnly = true)
    public Map<Role, Integer> getAllRoles() {
        Role[] allRoles = Role.values();

        Map<Role, Integer> roles = new HashMap<>();
        Arrays.stream(allRoles).forEach(role -> roles.put(role, 0));

        return roles;
    }

    @Transactional
    public void addSelectedUsersToProject(Long projectId, Integer count, List<Long> selectedUserIds) {
        Project project = getProjectById(projectId);

        List<User> selectedUsers = userRepository.findAllById(selectedUserIds);

        for (int i = 0; i < count; i++) {
            project.getCoworkers().addAll(selectedUsers);
        }

        projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public Map<String, List<User>> findTeamMembersByRoleAndProject(Long projectId) {
        Map<String, List<User>> teamMembersByRole = new HashMap<>();

        Project project = projectRepository.getProjectById(projectId);

        if (project != null) {
            Collection<User> teamMembers = project.getCoworkers();

            for (User user : teamMembers) {
                for (Role role : user.getUserRoles()) {
                    teamMembersByRole.computeIfAbsent(role.toString(), k -> new ArrayList<>()).add(user);
                }
            }
        }

        return teamMembersByRole;
    }

}
