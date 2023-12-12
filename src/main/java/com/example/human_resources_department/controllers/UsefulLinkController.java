package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.UsefulLink;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.UsefulLinkRepository;
import com.example.human_resources_department.services.UsefulLinkService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/useful-links")
public class UsefulLinkController {

    private final UsefulLinkService usefulLinkService;
    private final UsefulLinkRepository usefulLinkRepository;

    public UsefulLinkController(
            UsefulLinkService usefulLinkService,
            UsefulLinkRepository usefulLinkRepository
    ) {
        this.usefulLinkService = usefulLinkService;
        this.usefulLinkRepository = usefulLinkRepository;
    }

    @GetMapping
    public String showUsefulLinks(
            @AuthenticationPrincipal User currentUser,
            Model model
    ) {

        List<UsefulLink> usefulLinks = usefulLinkService.findAllLinksByOwner(currentUser);

        model.addAttribute("usefulLinkList", usefulLinks);

        return "usefulLinks";
    }

    @GetMapping("/add")
    public String showAddUsefulLinkForm() {
        return "addUsefulLink";
    }

    @PostMapping("/add")
    public String addUsefulLink(
            @AuthenticationPrincipal User currenUser,
            @ModelAttribute UsefulLink usefulLink
    ) {
        if (currenUser != null) {
            usefulLink.setOwner(currenUser);
            usefulLinkRepository.save(usefulLink);
        }

        return "redirect:/useful-links";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsefulLink(
            @PathVariable Long id
    ) {
        usefulLinkRepository.deleteById(id);

        return "redirect:/useful-links";
    }
}
