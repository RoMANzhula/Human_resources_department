package com.example.human_resources_department.services;

import com.example.human_resources_department.dto.VacancyDTO;
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
import java.util.stream.Collectors;


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
    public List<VacancyDTO> getAllVacancies() {
        List<Vacancy> allVacancies = vacancyRepository.findAll();

        if (allVacancies != null && !allVacancies.isEmpty()) {
            return allVacancies.stream()
                    .map(VacancyDTO::fromVacancy)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional(readOnly = true)
    public VacancyDTO getVacancyDTOById(Long vacancyId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vacancy ID: " + vacancyId));

        return VacancyDTO.fromVacancy(vacancy);
    }

    @Transactional
    public void createVacancy(
            User currentUser,
            VacancyDTO vacancyDTO,
            Long selectedProjectId
    ) {
        Project selectedProject = projectRepository.findById(selectedProjectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID: " + selectedProjectId));

        if (vacancyDTO.getDateOfStartJob().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start job must be in the future");
        }

        Vacancy vacancy = vacancyDTO.toVacancy();
        vacancy.setActive(Boolean.TRUE);
        vacancy.setDateOfCreate(new Date());
        vacancy.setAuthorVacancy(currentUser);
        vacancy.setVacancyProject(selectedProject);

        vacancyRepository.save(vacancy);
    }

    @Transactional
    public void editVacancy(
            User currentUser,
            Long id,
            Long selectedProjectId,
            Boolean isActive,
            VacancyDTO updatedVacancyDTO
    ) {
        Vacancy existingVacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vacancy ID: " + id));

        if (existingVacancy.getAuthorVacancy().equals(currentUser)) {
            throw new IllegalArgumentException("You don't have permission to edit this vacancy.");
        }

        existingVacancy.setProfession(updatedVacancyDTO.getProfession());
        existingVacancy.setProfLevel(updatedVacancyDTO.getProfLevel());
        existingVacancy.setEnglishLevel(updatedVacancyDTO.getEnglishLevel());
        existingVacancy.setVacancyDescription(updatedVacancyDTO.getVacancyDescription());
        existingVacancy.setSkillsDescription(updatedVacancyDTO.getSkillsDescription());

        existingVacancy.setDateOfCreate(new Date());
        existingVacancy.setDateOfStartJob(updatedVacancyDTO.getDateOfStartJob());

        existingVacancy.setActive(isActive);

        if (selectedProjectId != null) {
            Project selectedProject = projectRepository.findById(selectedProjectId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid project ID: " + selectedProjectId));

            existingVacancy.setVacancyProject(selectedProject);
        }

        vacancyRepository.save(existingVacancy);

    }
}
