package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Skill;
import com.example.human_resources_department.repositories.SkillRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    @PersistenceContext
    private EntityManager entityManager;

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Transactional(readOnly = true)
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Transactional
    public Skill createSkill(Skill newSkill) {
        return skillRepository.save(newSkill);
    }

    @Transactional(readOnly = true)
    public List<Skill> searchSkillsByName(String filterName) {
        if (filterName != null && !filterName.isEmpty()) {
            return skillRepository.findByNameContainingIgnoreCase(filterName);
        } else {
            return skillRepository.findAll();
        }

    }

    @Transactional(readOnly = true)
    public List<Skill> searchSkillsByNameAndTag(String name, String tag) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Skill> criteriaQuery = criteriaBuilder.createQuery(Skill.class);
        Root<Skill> root = criteriaQuery.from(Skill.class);

        List<Predicate> predicates = new ArrayList<>();

        // Додавання умов для пошуку за ім'ям
        if (name != null && !name.isEmpty()) {
            predicates.add(
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("name")),
                                    "%" + name.toLowerCase() + "%")
            );
        }

        // Додавання умов для пошуку за тегом
        if (tag != null && !tag.isEmpty()) {
            predicates.add(
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("tag")),
                                    "%" + tag.toLowerCase() + "%")
            );
        }

        // Формування умови для пошуку
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        // Виконання запиту
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
