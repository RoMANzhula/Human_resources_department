package com.example.human_resources_department.dto;

import com.example.human_resources_department.models.*;

import java.time.LocalDateTime;
import java.util.*;

public class ProjectDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final String fileContractName;
    private final String budget;
    private final boolean isActive;
    private final String customer;
    private final LocalDateTime deadline;
    private final String projectDirection;
    private final User authorProject;
    private final Date dateOfRegistration;
    private final Date dateOfCompletion;
    private final Map<Role, Integer> rolesAndCounts;
    private final Collection<User> coworkers;
    private final Set<Meeting> meetings;
    private final Collection<Vacancy> vacancies;

    public ProjectDTO(
            Long id,
            String name,
            String description,
            String fileContractName,
            String budget,
            boolean isActive,
            String customer,
            LocalDateTime deadline,
            String projectDirection,
            User authorProject,
            Date dateOfRegistration,
            Date dateOfCompletion,
            Map<Role, Integer> rolesAndCounts,
            Collection<User> coworkers,
            Set<Meeting> meetings,
            Collection<Vacancy> vacancies
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fileContractName = fileContractName;
        this.budget = budget;
        this.isActive = isActive;
        this.customer = customer;
        this.deadline = deadline;
        this.projectDirection = projectDirection;
        this.authorProject = authorProject;
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfCompletion = dateOfCompletion;
        this.rolesAndCounts = rolesAndCounts;
        this.coworkers = coworkers;
        this.meetings = meetings;
        this.vacancies = vacancies;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFileContractName() {
        return fileContractName;
    }

    public String getBudget() {
        return budget;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getCustomer() {
        return customer;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getProjectDirection() {
        return projectDirection;
    }

    public User getAuthorProject() {
        return authorProject;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public Map<Role, Integer> getRolesAndCounts() {
        return rolesAndCounts;
    }

    public Collection<User> getCoworkers() {
        return coworkers;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public Collection<Vacancy> getVacancies() {
        return vacancies;
    }

    public static ProjectDTO fromProjectDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getFileContractName(),
                project.getBudget(),
                project.isActive(),
                project.getCustomer(),
                project.getDeadline(),
                project.getProjectDirection(),
                project.getAuthor(),
                project.getDateOfRegistration(),
                project.getDateOfCompletion(),
                project.getRolesAndCounts(),
                project.getCoworkers(),
                project.getMeetings(),
                project.getVacancies()
        );
    }

    public Project toProjectDTO() {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setDescription(description);
        project.setFileContractName(fileContractName);
        project.setBudget(budget);
        project.setActive(isActive);
        project.setCustomer(customer);
        project.setDeadline(deadline);
        project.setProjectDirection(projectDirection);
        project.setAuthor(authorProject);
        project.setDateOfRegistration(dateOfRegistration);
        project.setDateOfCompletion(dateOfCompletion);
        project.setRolesAndCounts(rolesAndCounts);
        project.setCoworkers(coworkers);
        project.setMeetings(meetings);
        project.setVacancies(vacancies);
        return project;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return isActive == that.isActive &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(fileContractName, that.fileContractName) &&
                Objects.equals(budget, that.budget) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(projectDirection, that.projectDirection) &&
                Objects.equals(authorProject, that.authorProject) &&
                Objects.equals(dateOfRegistration, that.dateOfRegistration) &&
                Objects.equals(dateOfCompletion, that.dateOfCompletion) &&
                Objects.equals(rolesAndCounts, that.rolesAndCounts) &&
                Objects.equals(coworkers, that.coworkers) &&
                Objects.equals(meetings, that.meetings) &&
                Objects.equals(vacancies, that.vacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, name, description, fileContractName, budget,
                isActive, customer, deadline, projectDirection,
                authorProject, dateOfRegistration, dateOfCompletion,
                rolesAndCounts, coworkers, meetings, vacancies
        );
    }
}
