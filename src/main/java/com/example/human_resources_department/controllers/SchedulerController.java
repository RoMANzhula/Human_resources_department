package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Scheduler;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.services.SchedulerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/scheduler")
public class SchedulerController {
    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping
    public String showSchedulerPage(
            @AuthenticationPrincipal User currentUser,
            Model model
    ) {
        Iterable<Scheduler> listOfMyTasks = schedulerService.getAllTasksByUser(currentUser);

        model.addAttribute("tasks", listOfMyTasks);

        return "schedulerPage";
    }

    @PostMapping
    public String createTask(
            @AuthenticationPrincipal User currentUser,
            @ModelAttribute("task") Scheduler task
    ) {
        try {
            if (task != null) {
                schedulerService.saveTask(task, currentUser);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Deadline must be in the future.");
        }

        return "redirect:/scheduler";
    }


    @PostMapping("/updateTask")
    public String updateTaskStatus(
            @RequestParam Long taskId,
            @RequestParam boolean isCompleted
    ) {
        schedulerService.updateTaskStatus(taskId, isCompleted);
        return "redirect:/scheduler";
    }

}
