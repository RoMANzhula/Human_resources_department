package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Meeting;
import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.MeetingRepository;
import com.example.human_resources_department.repositories.ProjectRepository;
import com.example.human_resources_department.repositories.UserRepository;
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
    private final UserRepository userRepository;

    public MeetingService(
            MeetingRepository meetingRepository,
            ProjectRepository projectRepository,
            UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createMeeting(
            Meeting meeting,
            User user,
            List<Long> selectedProjects,
            List<Long> coworkersOnMeeting
    ) {
        List<Project> allSelectedProjects = projectRepository.findAllById(selectedProjects);
        List<User> allSelectedCoworkers = userRepository.findAllById(coworkersOnMeeting);

        if (meeting.getDateOfEvent().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Deadline must be in the future");
        }

        meeting.setStaff(allSelectedCoworkers);
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
}
