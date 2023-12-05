package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Skill;
import com.example.human_resources_department.services.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillsController {
    private final SkillService skillService;

    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public String showAllSkills(Model model) {
        List<Skill> allSkills = skillService.getAllSkills();

        model.addAttribute("allSkills", allSkills);
        model.addAttribute("newSkill", new Skill());

        return "skills";
    }

    @PostMapping("/create")
    public String createSkill(
            @ModelAttribute Skill newSkill
    ) {
        skillService.createSkill(newSkill);

        return "redirect:/skills";
    }

    @GetMapping("/search")
    public String searchSkills(
            @RequestParam(required = false) String name,
            Model model
    ) {
        List<Skill> skills;

        if (name != null) {
            skills = skillService.searchSkillsByName(name);
        } else {
            skills = skillService.getAllSkills();
        }

        if (skills != null && !skills.isEmpty()) {
            model.addAttribute("skills", skills);
        } else {
            model.addAttribute("allSkills", Collections.emptyList());
        }

        model.addAttribute("newSkill", new Skill());

        return "skills";
    }

}
