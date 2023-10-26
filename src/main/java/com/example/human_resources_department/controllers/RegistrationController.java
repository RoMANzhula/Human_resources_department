package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.UserRepository;
import com.example.human_resources_department.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Date;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;
    private final UserService userService;

    public RegistrationController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addHR_Manager(
            User user,
            Model model
    ) {

        if (!userService.addUser(user)) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
