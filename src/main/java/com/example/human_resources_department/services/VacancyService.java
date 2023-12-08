package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Vacancy;
import com.example.human_resources_department.repositories.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public List<Vacancy> getAllVacancies() {
        List<Vacancy> allVacancies = vacancyRepository.findAll();

        if (allVacancies != null && !allVacancies.isEmpty()) {
            return allVacancies;
        } else {
            return Collections.emptyList();
        }
    }
}
