package com.example.human_resources_department.dto;

import com.example.human_resources_department.models.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean active;
    private String activationCode;
    private String secretCodeWithRegistration;
    private Set<Role> userRoles;
    private Date dateOfRegistration;
    private List<Message> messages;
    private List<Scheduler> scheduler;
    private Set<Project> projects;
    private Set<Meeting> meetings;
    private Set<Meeting> meeting;
    private List<Meeting> staff_of_meeting;
    private Set<Skill> skills;
    private List<Vacancy> vacancies;
    private Set<UsefulLink> usefulLinks;
    private Set<Project> projectsByAuthor;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, String email, String phone, boolean active,
                   String activationCode, String secretCodeWithRegistration, Set<Role> userRoles,
                   Date dateOfRegistration, List<Message> messages, List<Scheduler> scheduler,
                   Set<Project> projects, Set<Meeting> meetings, Set<Meeting> meeting,
                   List<Meeting> staff_of_meeting, Set<Skill> skills, List<Vacancy> vacancies,
                   Set<UsefulLink> usefulLinks, Set<Project> projectsByAuthor) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.activationCode = activationCode;
        this.secretCodeWithRegistration = secretCodeWithRegistration;
        this.userRoles = userRoles;
        this.dateOfRegistration = dateOfRegistration;
        this.messages = messages;
        this.scheduler = scheduler;
        this.projects = projects;
        this.meetings = meetings;
        this.meeting = meeting;
        this.staff_of_meeting = staff_of_meeting;
        this.skills = skills;
        this.vacancies = vacancies;
        this.usefulLinks = usefulLinks;
        this.projectsByAuthor = projectsByAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getSecretCodeWithRegistration() {
        return secretCodeWithRegistration;
    }

    public void setSecretCodeWithRegistration(String secretCodeWithRegistration) {
        this.secretCodeWithRegistration = secretCodeWithRegistration;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Scheduler> getScheduler() {
        return scheduler;
    }

    public void setScheduler(List<Scheduler> scheduler) {
        this.scheduler = scheduler;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Set<Meeting> getMeeting() {
        return meeting;
    }

    public void setMeeting(Set<Meeting> meeting) {
        this.meeting = meeting;
    }

    public List<Meeting> getStaff_of_meeting() {
        return staff_of_meeting;
    }

    public void setStaff_of_meeting(List<Meeting> staff_of_meeting) {
        this.staff_of_meeting = staff_of_meeting;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public Set<UsefulLink> getUsefulLinks() {
        return usefulLinks;
    }

    public void setUsefulLinks(Set<UsefulLink> usefulLinks) {
        this.usefulLinks = usefulLinks;
    }

    public Set<Project> getProjectsByAuthor() {
        return projectsByAuthor;
    }

    public void setProjectsByAuthor(Set<Project> projectsByAuthor) {
        this.projectsByAuthor = projectsByAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (active != userDTO.active) return false;
        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
        if (username != null ? !username.equals(userDTO.username) : userDTO.username != null) return false;
        if (password != null ? !password.equals(userDTO.password) : userDTO.password != null) return false;
        if (email != null ? !email.equals(userDTO.email) : userDTO.email != null) return false;
        if (phone != null ? !phone.equals(userDTO.phone) : userDTO.phone != null) return false;
        if (activationCode != null ? !activationCode.equals(userDTO.activationCode) : userDTO.activationCode != null)
            return false;
        if (secretCodeWithRegistration != null ? !secretCodeWithRegistration.equals(userDTO.secretCodeWithRegistration) : userDTO.secretCodeWithRegistration != null)
            return false;
        if (userRoles != null ? !userRoles.equals(userDTO.userRoles) : userDTO.userRoles != null) return false;
        if (dateOfRegistration != null ? !dateOfRegistration.equals(userDTO.dateOfRegistration) : userDTO.dateOfRegistration != null)
            return false;
        if (messages != null ? !messages.equals(userDTO.messages) : userDTO.messages != null) return false;
        if (scheduler != null ? !scheduler.equals(userDTO.scheduler) : userDTO.scheduler != null) return false;
        if (projects != null ? !projects.equals(userDTO.projects) : userDTO.projects != null) return false;
        if (meetings != null ? !meetings.equals(userDTO.meetings) : userDTO.meetings != null) return false;
        if (meeting != null ? !meeting.equals(userDTO.meeting) : userDTO.meeting != null) return false;
        if (staff_of_meeting != null ? !staff_of_meeting.equals(userDTO.staff_of_meeting) : userDTO.staff_of_meeting != null)
            return false;
        if (skills != null ? !skills.equals(userDTO.skills) : userDTO.skills != null) return false;
        if (vacancies != null ? !vacancies.equals(userDTO.vacancies) : userDTO.vacancies != null) return false;
        if (usefulLinks != null ? !usefulLinks.equals(userDTO.usefulLinks) : userDTO.usefulLinks != null) return false;
        return projectsByAuthor != null ? projectsByAuthor.equals(userDTO.projectsByAuthor) : userDTO.projectsByAuthor == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (activationCode != null ? activationCode.hashCode() : 0);
        result = 31 * result + (secretCodeWithRegistration != null ? secretCodeWithRegistration.hashCode() : 0);
        result = 31 * result + (userRoles != null ? userRoles.hashCode() : 0);
        result = 31 * result + (dateOfRegistration != null ? dateOfRegistration.hashCode() : 0);
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        result = 31 * result + (scheduler != null ? scheduler.hashCode() : 0);
        result = 31 * result + (projects != null ? projects.hashCode() : 0);
        result = 31 * result + (meetings != null ? meetings.hashCode() : 0);
        result = 31 * result + (meeting != null ? meeting.hashCode() : 0);
        result = 31 * result + (staff_of_meeting != null ? staff_of_meeting.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        result = 31 * result + (vacancies != null ? vacancies.hashCode() : 0);
        result = 31 * result + (usefulLinks != null ? usefulLinks.hashCode() : 0);
        result = 31 * result + (projectsByAuthor != null ? projectsByAuthor.hashCode() : 0);
        return result;
    }
}
