package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.models.Vacancy;
import com.example.human_resources_department.repositories.ProjectRepository;
import com.example.human_resources_department.repositories.VacancyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final ProjectRepository projectRepository;

    public VacancyService(VacancyRepository vacancyRepository,
                          ProjectRepository projectRepository) {
        this.vacancyRepository = vacancyRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public List<Vacancy> getAllVacancies() {
        List<Vacancy> allVacancies = vacancyRepository.findAll();

        if (allVacancies != null && !allVacancies.isEmpty()) {
            return allVacancies;
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional(readOnly = true)
    public Vacancy getVacancyById(Long vacancyId) {
        return vacancyRepository.findById(vacancyId).orElse(null);
    }

    @Transactional
    public void createVacancy(User currentUser, Vacancy vacancy, Long selectedProjectId) {
        Project selectedProject = projectRepository.findById(selectedProjectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID: " + selectedProjectId));

        if (vacancy.getDateOfStartJob().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start job must be in the future");
        }

        vacancy.setDateOfCreate(new Date());
        vacancy.setAuthorVacancy(currentUser);
        vacancy.setVacancyProject(selectedProject);

        vacancyRepository.save(vacancy);
    }
}
