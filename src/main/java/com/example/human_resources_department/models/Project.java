package com.example.human_resources_department.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String fileContractName;
    private String budget;
    private boolean isActive;
    private String customer;
    private LocalDateTime deadline;
    private String projectDirection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User authorProject;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date dateOfRegistration;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "completion_date")
    private Date dateOfCompletion;

    @ElementCollection
    @CollectionTable(
            name = "project_roles",
            joinColumns = @JoinColumn(name = "project_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "user_count")
    private Map<Role, Integer> rolesAndCounts = new HashMap<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_members",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> coworkers;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    private Set<Meeting> meetings = new HashSet<>();

    @OneToMany(mappedBy = "vacancyProject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Vacancy> vacancies = new ArrayList<>();

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileContractName() {
        return fileContractName;
    }

    public void setFileContractName(String fileContractName) {
        this.fileContractName = fileContractName;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getProjectDirection() {
        return projectDirection;
    }

    public void setProjectDirection(String projectDirection) {
        this.projectDirection = projectDirection;
    }

    public User getAuthor() {
        return authorProject;
    }

    public void setAuthor(User authorProject) {
        this.authorProject = authorProject;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Collection<User> getCoworkers() {
        return coworkers;
    }

    public void setCoworkers(Collection<User> coworkers) {
        this.coworkers = coworkers;
    }

    public Map<Role, Integer> getRolesAndCounts() {
        return rolesAndCounts;
    }

    public void setRolesAndCounts(Map<Role, Integer> rolesAndCounts) {
        this.rolesAndCounts = rolesAndCounts;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Collection<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Collection<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
}
