package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.User;
import com.example.human_resources_department.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController { //for HR-managers registration
    private final UserService userService;

    public RegistrationController(UserService userService) {
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

        return "duringRegistration";
    }

    @GetMapping("/duringRegistration")
    public String duringRegistrationPage() {
        return "duringRegistration";
    }

    @GetMapping("/activate/{activationCode}")
    public String activateRegistrationCode(
            @PathVariable String activationCode,
            Model model
    ) {
        boolean isActivatedUser = userService.activateUserByActivationCode(activationCode);

        if (isActivatedUser) {
            model.addAttribute("message", "User activation SUCCESSFUL!");
        } else {
            model.addAttribute("message", "Activation code NOT FOUND!");
        }

        return "login";
    }
}
