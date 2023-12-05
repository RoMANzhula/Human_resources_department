package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Skill;
import com.example.human_resources_department.repositories.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillService {

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

    public List<Skill> searchSkillsByName(String name) {
        return skillRepository.findByNameContainingIgnoreCase(name);
    }
}
