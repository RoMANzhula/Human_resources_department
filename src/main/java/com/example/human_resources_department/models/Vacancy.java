package com.example.human_resources_department.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String profession;
    private String profLevel;
    private String englishLevel;
    private String vacancyDescription;
    private String skillsDescription;
    private boolean isActive = true;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date dateOfCreate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "start_job_date")
    private LocalDateTime dateOfStartJob;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User authorVacancy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project vacancyProject;

    public Vacancy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfLevel() {
        return profLevel;
    }

    public void setProfLevel(String profLevel) {
        this.profLevel = profLevel;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getVacancyDescription() {
        return vacancyDescription;
    }

    public void setVacancyDescription(String vacancyDescription) {
        this.vacancyDescription = vacancyDescription;
    }

    public String getSkillsDescription() {
        return skillsDescription;
    }

    public void setSkillsDescription(String skillsDescription) {
        this.skillsDescription = skillsDescription;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public LocalDateTime getDateOfStartJob() {
        return dateOfStartJob;
    }

    public void setDateOfStartJob(LocalDateTime dateOfStartJob) {
        this.dateOfStartJob = dateOfStartJob;
    }

    public User getAuthorVacancy() {
        return authorVacancy;
    }

    public void setAuthorVacancy(User authorVacancy) {
        this.authorVacancy = authorVacancy;
    }

    public Project getVacancyProject() {
        return vacancyProject;
    }

    public void setVacancyProject(Project vacancyProject) {
        this.vacancyProject = vacancyProject;
    }
}
