package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Date;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "/registration";
    }

    @PostMapping("/registration")
    public String addHR_Manager(
            User user,
            Model model
    ) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }

        user.setActive(true); //come as active user
        user.setRoles(Collections.singleton(Role.HR_MANAGER)); //only for hr-manager
        user.setDateOfRegistration(new Date()); //install date of registration
        userRepository.save(user);

        return "redirect:/login";
    }
}
