package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Meeting;
import com.example.human_resources_department.repositories.MeetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingService(
            MeetingRepository meetingRepository
    ) {
        this.meetingRepository = meetingRepository;
    }

    @Transactional
    public void createMeeting(Meeting meeting) {
        if (meeting.getDateOfEvent().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Deadline must be in the future");
        }
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
