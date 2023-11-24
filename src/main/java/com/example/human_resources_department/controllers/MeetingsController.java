package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Meeting;
import com.example.human_resources_department.services.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/meetings")
public class MeetingsController {

    private final MeetingService meetingService;

    public MeetingsController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/create")
    public String showCreatingMeetingForm(
            Model model
    ) {
        Meeting newMeeting = new Meeting();

        model.addAttribute("meeting", newMeeting);

        return "createMeeting";
    }

    @PostMapping("/create")
    public String createMeeting(
            @ModelAttribute Meeting meeting
    ) {
        try {
            meetingService.createMeeting(meeting);
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
