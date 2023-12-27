package com.example.human_resources_department.services;

import com.example.human_resources_department.dto.ProjectDTO;
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
    public ProjectDTO findProjectDTOById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return (project != null) ? ProjectDTO.fromProjectDTO(project) : null;
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
    public ProjectDTO getProjectDTOById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            return ProjectDTO.fromProjectDTO(project);
        } else {
            return null;
        }
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
            ProjectDTO projectDTO,
            Integer count,
            List<Long> selectedUserIds
    ) {
        Long projectId = projectDTO.getId();
        Project project = getProjectDTOById(projectId).toProjectDTO();
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

        ProjectDTO projectDTO = ProjectDTO.fromProjectDTO(projectRepository.getProjectById(projectId));

        if (projectDTO != null) {
            Collection<User> teamMembers = projectDTO.getCoworkers();

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
        ProjectDTO projectDTO = ProjectDTO.fromProjectDTO(projectRepository.getProjectById(projectId));

        if (projectDTO != null && projectDTO.getCoworkers() != null) {
            return new ArrayList<>(projectDTO.getCoworkers());
        } else {
            return Collections.emptyList();
        }
    }


    @Transactional
    public void clearSelectedUsersForProject(ProjectDTO projectDTO) {
        Long projectId = projectDTO.getId();
        Project project = projectRepository.getProjectById(projectId);

        if (project != null) {
            project.setCoworkers(Collections.emptySet());
            projectRepository.save(project);
        }
    }


    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return projectRepository.findAll();

    }

    @Transactional
    public void editProject(
            User currentUser,
            Long projectId,
            MultipartFile fileContractName,
            Boolean isActive,
            ProjectDTO editedProjectDTO
    ) {
        Project existingProject = projectRepository.getProjectById(projectId);

        if (existingProject.getAuthor().equals(currentUser)) {
            throw new IllegalArgumentException("You don't have permission to edit this meeting.");
        }

        existingProject.setName(editedProjectDTO.getName());
        existingProject.setDescription(editedProjectDTO.getDescription());
        existingProject.setBudget(editedProjectDTO.getBudget());
        existingProject.setActive(isActive);
        existingProject.setCustomer(editedProjectDTO.getCustomer());
        existingProject.setDeadline(editedProjectDTO.getDeadline());
        existingProject.setProjectDirection(editedProjectDTO.getProjectDirection());

        if (fileContractName != null && !fileContractName.getOriginalFilename().isEmpty()) {
            String uniqueFileName = localFileStorageService.storeFile(fileContractName);
            existingProject.setFileContractName(uniqueFileName);
        }

        projectRepository.save(existingProject);

    }

}
