package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.UserRepository;
import com.example.human_resources_department.services.UserService;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/coworker")
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @GetMapping
    public String filterCoworkerList(
            @RequestParam(required = false, defaultValue = "") String usernameFilter,
            Model model
    ) {
        Iterable<User> listOfCoworkersByFilter = userService.getUsersByUsername(usernameFilter);

        model.addAttribute("coworkers", listOfCoworkersByFilter);
        model.addAttribute("usernameFilter", usernameFilter);

        return "coworkerList";
    }

    @GetMapping("{coworker}")
    public String coworkersProfileEditor(
            @PathVariable User coworker,
            Model model
    ) {
        model.addAttribute("coworker", coworker);
        model.addAttribute("roles", Role.values());

        return "coworkerProfile";
    }


    @PostMapping
    public String coworkerProfileEditorSave(
            @RequestParam Boolean isActive,
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam(value = "coworkerId") User coworker
    ){
        userService.updateCoworker(coworker, isActive, username, form);

        return "redirect:/coworker";
    }
}