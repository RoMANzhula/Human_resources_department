package com.example.human_resources_department.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String topic;
    private String description;

    @OneToMany(mappedBy = "meetingSpeakers", cascade = CascadeType.ALL)
    private Set<User> speakers = new HashSet<>();

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date dateOfRegistration;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User authorOfMeeting;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "event_date")
    private LocalDateTime dateOfEvent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff_of_meeting")
    private Set<User> staff = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Meeting() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<User> speakers) {
        this.speakers = speakers;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public User getAuthorOfMeeting() {
        return authorOfMeeting;
    }

    public void setAuthorOfMeeting(User authorOfMeeting) {
        this.authorOfMeeting = authorOfMeeting;
    }

    public LocalDateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(LocalDateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public Set<User> getStaff() {
        return staff;
    }

    public void setStaff(Set<User> staff) {
        this.staff = staff;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
