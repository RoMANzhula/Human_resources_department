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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/meetings")
public class MeetingsController {

    private final MeetingService meetingService;
    private final UserService userService;
    private final ProjectService projectService;

    public MeetingsController(
            MeetingService meetingService,
            UserService userService,
            ProjectService projectService
    ) {
        this.meetingService = meetingService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping
    public String showAllMeetings(
            Model model
    ) {
        List<Meeting> meetings = meetingService.getAllMeetings();

        model.addAttribute("meetings", meetings);

        return "allMeetings";
    }

    @GetMapping("/create")
    public String showCreatingMeetingForm(
            Model model
    ) {
        Meeting newMeeting = new Meeting();
        List<Project> allProjects = projectService.getAllProjects();
        List<User> allUsers = userService.getAllUsers();

        model.addAttribute("meeting", newMeeting);
        model.addAttribute("allProjects", allProjects);
        model.addAttribute("allUsers", allUsers);

        return "createMeeting";
    }

    @PostMapping("/save")
    public String saveMeeting(
            @AuthenticationPrincipal User user,
            @ModelAttribute Meeting meeting,
            @RequestParam(name = "selectedProjects", required = false) List<Long> selectedProjects,
            @RequestParam(name = "selectedUsers", required = false) List<Long> selectedUsers
    ) {
        try {
            meetingService.createMeeting(meeting, user, selectedProjects, selectedUsers);
            return "redirect:/meetings/details/" + meeting.getId();
        } catch (IllegalArgumentException e) {
            System.out.println("Deadline must be in the future.");
            return "redirect:/meetings/create";
        }
    }

    @GetMapping("/details/{meetingId}")
    public String showMeetingDetails(
            @PathVariable Long meetingId,
            Model model
    ) {
        Meeting meeting = meetingService.getMeetingById(meetingId);
        List<Project> meetingProjects = meeting.getProjects();

        if (!meetingProjects.isEmpty()) {
            List<User> coworkersByProjects = userService.getCoworkersByProjectId(
                    meetingProjects.stream().map(Project::getId).collect(Collectors.toList())
            );

            model.addAttribute("meeting", meeting);
            model.addAttribute("allUsers", coworkersByProjects);
        } else {
            model.addAttribute("meeting", meeting);
            model.addAttribute("allUsers", Collections.emptyList());
            model.addAttribute("errorMessage", "No projects selected");
        }

        return "meetingDetails";
    }

    @PostMapping("/details/{meetingId}/addCoworkers")
    public String addCoworkersToMeeting(
            @PathVariable Long meetingId,
            @RequestParam(name = "coworkers", required = false) List<Long> coworkers,
            Model model
    ) {
        Meeting meeting = meetingService.getMeetingById(meetingId);

        if (coworkers != null && !coworkers.isEmpty()) {
            meetingService.addCoworkersToMeeting(meeting, coworkers);
        } else {
            model.addAttribute("error", "Please select coworkers.");
        }

        return "redirect:/meetings";
    }

}
