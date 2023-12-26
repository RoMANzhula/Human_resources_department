package com.example.human_resources_department.dto;

import com.example.human_resources_department.models.Meeting;
import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MeetingDTO {
    private final Long id;
    private final String topic;
    private final String description;
    private final String link;
    private final List<User> speakers;
    private final LocalDateTime dateOfEvent;
    private final List<User> staff;
    private final List<Project> projects;
    private final User authorOfMeeting;
    private final Date dateOfRegistration;


    public MeetingDTO(
            Long id, String topic, String description, String link,
            List<User> speakers, LocalDateTime dateOfEvent,
            List<User> staff, List<Project> projects,
            User authorOfMeeting, Date dateOfRegistration
    ) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.link = link;
        this.speakers = new ArrayList<>(speakers);
        this.dateOfEvent = dateOfEvent;
        this.staff = new ArrayList<>(staff);
        this.projects = new ArrayList<>(projects);
        this.authorOfMeeting = authorOfMeeting;
        this.dateOfRegistration = dateOfRegistration;
    }

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public List<User> getSpeakers() {
        return new ArrayList<>(speakers);
    }

    public LocalDateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public List<User> getStaff() {
        return new ArrayList<>(staff);
    }

    public List<Project> getProjects() {
        return new ArrayList<>(projects);
    }

    public User getAuthorOfMeeting() {
        return authorOfMeeting;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public static MeetingDTO fromMeetingEntity(Meeting meeting) {
        return new MeetingDTO(
                meeting.getId(),
                meeting.getTopic(),
                meeting.getDescription(),
                meeting.getLink(),
                new ArrayList<>(meeting.getSpeakers()),
                meeting.getDateOfEvent(),
                new ArrayList<>(meeting.getStaff()),
                new ArrayList<>(meeting.getProjects()),
                meeting.getAuthorOfMeeting(),
                meeting.getDateOfRegistration()
        );
    }

    public Meeting toMeetingEntity() {
        Meeting meeting = new Meeting();
        meeting.setId(this.getId());
        meeting.setTopic(this.getTopic());
        meeting.setDescription(this.getDescription());
        meeting.setLink(this.getLink());
        meeting.setDateOfEvent(this.getDateOfEvent());

        List<User> speakers = this.getSpeakers();
        List<User> staff = this.getStaff();
        List<Project> projects = this.getProjects();

        meeting.setSpeakers(speakers);
        meeting.setStaff(staff);
        meeting.setProjects(projects);
        meeting.setAuthorOfMeeting(this.getAuthorOfMeeting());
        meeting.setDateOfRegistration(this.getDateOfRegistration());

        return meeting;
    }

    public MeetingDTO withAdditionalStaff(List<User> additionalStaff) {
        List<User> newStaff = new ArrayList<>(this.staff);
        newStaff.addAll(additionalStaff);
        return new MeetingDTO(
                this.id, this.topic, this.description, this.link,
                new ArrayList<>(this.speakers), this.dateOfEvent,
                newStaff, new ArrayList<>(this.projects),
                this.authorOfMeeting, this.dateOfRegistration
        );
    }

}
