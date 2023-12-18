package com.example.human_resources_department.dto;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.models.Vacancy;

import java.time.LocalDateTime;
import java.util.Date;

public class VacancyDTO {
    private final Long id;
    private final String profession;
    private final String profLevel;
    private final String englishLevel;
    private final String vacancyDescription;
    private final String skillsDescription;
    private final Boolean isActive;
    private final Date dateOfCreate;
    private final LocalDateTime dateOfStartJob;
    private final User authorVacancy;
    private final Project vacancyProject;


    public VacancyDTO(
            Long id,
            String profession,
            String profLevel,
            String englishLevel,
            String vacancyDescription,
            String skillsDescription,
            Boolean isActive,
            Date dateOfCreate,
            LocalDateTime dateOfStartJob,
            User authorVacancy,
            Project vacancyProject
    ) {
        this.id = id;
        this.profession = profession;
        this.profLevel = profLevel;
        this.englishLevel = englishLevel;
        this.vacancyDescription = vacancyDescription;
        this.skillsDescription = skillsDescription;
        this.isActive = (isActive != null) ? isActive : true;
        this.dateOfCreate = dateOfCreate != null ? new Date(dateOfCreate.getTime()) : null;
        this.dateOfStartJob = dateOfStartJob;
        this.authorVacancy = authorVacancy;
        this.vacancyProject = vacancyProject;
    }

    public Long getId() {
        return id;
    }

    public String getProfession() {
        return profession;
    }

    public String getProfLevel() {
        return profLevel;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public String getVacancyDescription() {
        return vacancyDescription;
    }

    public String getSkillsDescription() {
        return skillsDescription;
    }

    public Boolean isActive() {
        return isActive;
    }

    public Date getDateOfCreate() {
        return dateOfCreate != null ? new Date(dateOfCreate.getTime()) : null;
    }

    public LocalDateTime getDateOfStartJob() {
        return dateOfStartJob;
    }

    public User getAuthorVacancy() {
        return authorVacancy;
    }

    public Project getVacancyProject() {
        return vacancyProject;
    }

    public static VacancyDTO fromVacancy(Vacancy vacancy) {
        return new VacancyDTO(
                vacancy.getId(),
                vacancy.getProfession(),
                vacancy.getProfLevel(),
                vacancy.getEnglishLevel(),
                vacancy.getVacancyDescription(),
                vacancy.getSkillsDescription(),
                vacancy.isActive(),
                vacancy.getDateOfCreate(),
                vacancy.getDateOfStartJob(),
                vacancy.getAuthorVacancy(),
                vacancy.getVacancyProject()
        );
    }

    public Vacancy toVacancy() {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(this.id);
        vacancy.setProfession(this.profession);
        vacancy.setProfLevel(this.profLevel);
        vacancy.setEnglishLevel(this.englishLevel);
        vacancy.setVacancyDescription(this.vacancyDescription);
        vacancy.setSkillsDescription(this.skillsDescription);
        vacancy.setActive(this.isActive);
        vacancy.setDateOfCreate(this.dateOfCreate);
        vacancy.setDateOfStartJob(this.dateOfStartJob);
        vacancy.setAuthorVacancy(this.authorVacancy);
        vacancy.setVacancyProject(this.vacancyProject);
        return vacancy;
    }
}
