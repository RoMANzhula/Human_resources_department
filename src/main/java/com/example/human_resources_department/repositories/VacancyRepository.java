package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

}
