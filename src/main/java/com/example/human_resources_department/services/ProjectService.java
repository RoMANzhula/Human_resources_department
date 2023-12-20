package com.example.human_resources_department.services;

import com.example.human_resources_department.dto.ProjectDTO;
import com.example.human_resources_department.dto.VacancyDTO;
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
import java.util.stream.Collectors;

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
    public void addSelectedUsersToProject(
            Long projectId,
            Integer count,
            List<Long> selectedUserIds
    ) {
        Project project = getProjectById(projectId);
        if (project == null) {
            throw new EntityNotFoundException("Project with ID " + projectId + " not found");
        }

        List<User> selectedUsers = userRepository.findAllById(selectedUserIds);
        if (selectedUsers.isEmpty()) {
            throw new EntityNotFoundException("No users found with the provided IDs");
        }

        int addedUsersCount = 0;
        for (User user : selectedUsers) {
            if (!project.getCoworkers().contains(user)) {
                project.getCoworkers().add(user);
                user.getProjects().add(project);
                addedUsersCount++;
            }

            if (addedUsersCount == count) {
                break;
            }
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

    @Transactional(readOnly = true)
    public List<User> getSelectedUsersForProject(Long projectId) {
        Project project = projectRepository.getProjectById(projectId);

        if (project != null && project.getCoworkers() != null) {
            return new ArrayList<>(project.getCoworkers());
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void clearSelectedUsersForProject(Long projectId) {
        Project project = projectRepository.getProjectById(projectId);

        if (project != null) {
            project.setCoworkers(Collections.emptySet());
            projectRepository.save(project);
        }
    }

//    @Transactional(readOnly = true)
//    public List<Project> getAllProjects() {
//        return projectRepository.findAll();
//    }

    @Transactional(readOnly = true)
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();

        if (projects != null && !projects.isEmpty()) {
            return projects.stream()
                    .map(ProjectDTO::fromProjectDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void editProject(
            User currentUser,
            Long projectId,
            MultipartFile fileContractName,
            Boolean isActive,
            Project editedProject
    ) {
        Project existingProject = projectRepository.getProjectById(projectId);

        if (existingProject.getAuthor().equals(currentUser)) {
            throw new IllegalArgumentException("You don't have permission to edit this meeting.");
        }

        existingProject.setName(editedProject.getName());
        existingProject.setDescription(editedProject.getDescription());
        existingProject.setBudget(editedProject.getBudget());
        existingProject.setActive(isActive);
        existingProject.setCustomer(editedProject.getCustomer());
        existingProject.setDeadline(editedProject.getDeadline());
        existingProject.setProjectDirection(editedProject.getProjectDirection());

        if (fileContractName != null && !fileContractName.getOriginalFilename().isEmpty()) {
            String uniqueFileName = localFileStorageService.storeFile(fileContractName);
            existingProject.setFileContractName(uniqueFileName);
        }

        projectRepository.save(existingProject);

    }

}
