package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Meeting;
import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.MeetingRepository;
import com.example.human_resources_department.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;

    public MeetingService(
            MeetingRepository meetingRepository,
            ProjectRepository projectRepository,
            UserService userService
    ) {
        this.meetingRepository = meetingRepository;
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Transactional
    public void createMeeting(
            Meeting meeting,
            User user,
            List<Long> selectedProjects
    ) {
        List<Project> allSelectedProjects = projectRepository.findAllById(selectedProjects);

        if (meeting.getDateOfEvent().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Deadline must be in the future");
        }

        meeting.setProjects(allSelectedProjects);
        meeting.setAuthorOfMeeting(user);
        meeting.setDateOfRegistration(new Date());

        meetingRepository.save(meeting);
    }

    @Transactional(readOnly = true)
    public List<Meeting> getAllMeetings() {
        List<Meeting> allMeetings = meetingRepository.findAll();

        if (allMeetings != null && !allMeetings.isEmpty()) {
            return allMeetings;
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional(readOnly = true)
    public Meeting getMeetingById(Long meetingId) {
        return meetingRepository.findById(meetingId).orElse(null);
    }

    @Transactional
    public void addCoworkersToMeeting(Meeting meeting, List<Long> projectIds) {
        List<User> currentCoworkers = meeting.getStaff();

        List<User> newCoworkers = userService.getCoworkersByProjectId(projectIds);

        currentCoworkers.addAll(newCoworkers);

        meeting.setStaff(currentCoworkers);

        meetingRepository.save(meeting);
    }
}
