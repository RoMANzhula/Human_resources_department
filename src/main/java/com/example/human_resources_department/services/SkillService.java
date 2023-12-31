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
        Skill existingSkill = skillRepository.findByName(newSkill.getName());

        if (existingSkill != null) {
            return existingSkill;
        }

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

        if (name != null && !name.isEmpty()) {
            predicates.add(
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("name")),
                                    "%" + name.toLowerCase() + "%")
            );
        }

        if (tag != null && !tag.isEmpty()) {
            predicates.add(
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("tag")),
                                    "%" + tag.toLowerCase() + "%")
            );
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
