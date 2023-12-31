package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Message;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.services.EmployeeService;
import com.example.human_resources_department.services.MessageService;
import com.example.human_resources_department.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/coworker")
public class UserController {
    private final EmployeeService employeeService;
    private final MessageService messageService;
    private final UserService userService;

    public UserController(
            EmployeeService employeeService,
            MessageService messageService,
            UserService userService
    ) {
        this.employeeService = employeeService;
        this.messageService = messageService;
        this.userService = userService;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('HR_MANAGER')")
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
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('HR_MANAGER')")
    public String coworkersProfileEditor(
            @PathVariable User coworker,
            Model model
    ) {
        model.addAttribute("coworker", coworker);
        model.addAttribute("roles", Role.values());

        return "coworkerProfile";
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('HR_MANAGER')")
    public String coworkerProfileEditorSave(
            @RequestParam Boolean isActive,
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam(value = "coworkerId") User coworker
    ){
        userService.updateCoworker(coworker, isActive, username, form);

        return "redirect:/coworker";
    }

    @GetMapping("/coworker-page/{coworkerId}")
    public String coworkerPage(
            @PathVariable Long coworkerId,
            Model model
    ) {
        User coworker = userService.getUserById(coworkerId);

        Iterable<Message> listOfMessagesThisCoworker = messageService.messagesListForCurrentUserById(coworkerId);

        Employee employee = employeeService
                .findEmployBySecretCodeWithRegistration(coworker.getSecretCodeWithRegistration());

        String employeeFullName = employee.getFirstName() + " " + employee.getSecondName();

        if (coworker != null) {
            model.addAttribute("coworker", coworker);
            model.addAttribute("messages", listOfMessagesThisCoworker);
            model.addAttribute("employeeName", employeeFullName);
            return "pageOfUserWeClickOn";
        } else {
            return "allMessages";
        }
    }

    @GetMapping("/profile")
    public String myOwnPage(
            @AuthenticationPrincipal User currentUser,
            Model model
    ) {
        model.addAttribute("coworker", currentUser);

        return "userOwnPage";
    }

    @GetMapping("/profile/{userId}")
    public String userOwnPage(
            @PathVariable Long userId,
            Model model
    ) {
        User requestedUser = userService.getUserById(userId);

        if (requestedUser == null) {
            return "errorPage";
        }

        model.addAttribute("coworker", requestedUser);

        return "userOwnPage";
    }

    @GetMapping("/edit")
    public String editMyOwnProfile(
            Model model,
            @AuthenticationPrincipal User currentUser
    ) {
        model.addAttribute("user", currentUser);

        return "userOwnPageProfile";
    }

    @PostMapping("/update")
    public String updateMyOwnProfile(
            @ModelAttribute User updatedUser,
            @AuthenticationPrincipal User currentUser
    ) {
        userService.updateMyInformation(updatedUser, currentUser);

        return "redirect:/coworker/profile";
    }

}
