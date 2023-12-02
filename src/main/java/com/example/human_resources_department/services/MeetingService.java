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
    private final UserService userService;
    private final UserRepository userRepository;

    public MeetingService(
            MeetingRepository meetingRepository,
            ProjectRepository projectRepository,
            UserService userService,
            UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createMeeting(
            Meeting meeting,
            User user,
            List<Long> selectedProjects,
            List<Long> selectedUsers
    ) {
        List<Project> allSelectedProjects = projectRepository.findAllById(selectedProjects);
        List<User> allSelectedUsers = userRepository.findAllById(selectedUsers);

        if (meeting.getDateOfEvent().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Deadline must be in the future");
        }

        meeting.setProjects(allSelectedProjects);
        meeting.setSpeakers(allSelectedUsers);
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

    public List<Meeting> getUserMeetings(User user) {
        List<Meeting> listOfUserMeetings = meetingRepository.findAllByStaffContaining(user);

        return listOfUserMeetings;
    }

    @Transactional
    public void editMeeting(
            User currentUser,
            Long meetingId,
            Meeting editedMeeting,
            List<Long> selectedProjects,
            List<Long> selectedUsers
    ) {
        Meeting existingMeeting = getMeetingById(meetingId);

        if (existingMeeting.getAuthorOfMeeting().equals(currentUser)) {
            throw new IllegalArgumentException("You don't have permission to edit this meeting.");
        }

        List<Project> allSelectedProjects = projectRepository.findAllById(selectedProjects);
        List<User> allSelectedUsers = userRepository.findAllById(selectedUsers);

        existingMeeting.setTopic(editedMeeting.getTopic());
        existingMeeting.setDateOfEvent(editedMeeting.getDateOfEvent());
        existingMeeting.setLink(editedMeeting.getLink());

        existingMeeting.getProjects().clear();
        existingMeeting.getSpeakers().clear();

        existingMeeting.setProjects(allSelectedProjects);
        existingMeeting.setSpeakers(allSelectedUsers);

        meetingRepository.save(existingMeeting);
    }

    @Transactional
    public void addCoworkersToMeetingEdit(
            Meeting existingMeeting,
            List<Long> selectedCoworkers
    ) {
        existingMeeting.getStaff().clear();

        List<User> currentCoworkers = existingMeeting.getStaff();

        List<User> allThisMeetingCoworkers = userRepository.findAllById(selectedCoworkers);

        currentCoworkers.addAll(allThisMeetingCoworkers);

        existingMeeting.setStaff(currentCoworkers);

        meetingRepository.save(existingMeeting);
    }

}
