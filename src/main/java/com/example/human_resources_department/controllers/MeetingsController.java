package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Meeting;
import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.services.MeetingService;
import com.example.human_resources_department.services.ProjectService;
import com.example.human_resources_department.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/meetings")
public class MeetingsController {

    private final MeetingService meetingService;
    private final UserService userService;
    private final ProjectService projectService;

    public MeetingsController(
            MeetingService meetingService,
            UserService userService,
            ProjectService projectService) {
        this.meetingService = meetingService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String showCreatingMeetingForm(
            Model model
    ) {
        Meeting newMeeting = new Meeting();
        List<User> allUsers = userService.getAllUsers();
        List<Project> allCheckedProjects = projectService.getAllProjects();

        model.addAttribute("meeting", newMeeting);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allProjects", allCheckedProjects);

        return "createMeeting";
    }

    @PostMapping("/create")
    public String createMeeting(
            @AuthenticationPrincipal User user,
            @ModelAttribute Meeting meeting,
            @RequestParam(name = "selectedProjects", required = false) List<Long> selectedProjects,
            @RequestParam(name = "meeting.coworkers", required = false) List<Long> coworkersOnMeeting
    ) {
        try {
            if (selectedProjects != null && !selectedProjects.isEmpty()
                || coworkersOnMeeting != null && coworkersOnMeeting.isEmpty()
            ) {
                meetingService.createMeeting(meeting, user, selectedProjects, coworkersOnMeeting);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Deadline must be in the future.");
        }

        return "redirect:/meetings";
    }

    @GetMapping
    public String showAllMeetings(
            Model model
    ) {
        List<Meeting> meetings = meetingService.getAllMeetings();

        model.addAttribute("meetings", meetings);

        return "allMeetings";
    }
}
