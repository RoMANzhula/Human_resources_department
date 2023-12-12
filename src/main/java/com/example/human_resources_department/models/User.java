package com.example.human_resources_department.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean active;
    private String activationCode;

    private String secretCodeWithRegistration;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING) //зберігаємо у вигляді строки до БД
    private Set<Role> userRoles = new HashSet<>();

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "reg_date", nullable = false)
    private Date dateOfRegistration;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Scheduler> scheduler = new ArrayList<>();

    @ManyToMany(mappedBy = "coworkers", fetch = FetchType.EAGER)
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(mappedBy = "speakers")
    private Set<Meeting> meetings = new HashSet<>();

    @OneToMany(mappedBy = "authorOfMeeting", cascade = CascadeType.ALL)
    private Set<Meeting> meeting;

    @ManyToMany(mappedBy = "staff")
    private List<Meeting> staff_of_meeting;

    @ManyToMany(mappedBy = "coworkers")
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "authorVacancy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Vacancy> vacancies = new ArrayList<>();


    @OneToMany(mappedBy = "owner")
    private Set<UsefulLink> usefulLinks = new HashSet<>();

    public User() {
    }

    public boolean isHR_Manager() { //перевіряємо чи являється користувач hr manager
        return userRoles.contains(Role.HR_MANAGER);
    }

    public boolean isProjectManager() { //перевіряємо чи являється користувач hr manager
        return userRoles.contains(Role.PROJECT_MANAGER);
    }


    public boolean isAdmin() {
        return userRoles.contains(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
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

    public String getSecretCodeWithRegistration() {
        return secretCodeWithRegistration;
    }

    public void setSecretCodeWithRegistration(String secretCodeWithRegistration) {
        this.secretCodeWithRegistration = secretCodeWithRegistration;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public Collection<Scheduler> getScheduler() {
        return scheduler;
    }

    public void setScheduler(Collection<Scheduler> scheduler) {
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

    public Collection<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Collection<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public Set<UsefulLink> getUsefulLinks() {
        return usefulLinks;
    }

    public void setUsefulLinks(Set<UsefulLink> usefulLinks) {
        this.usefulLinks = usefulLinks;
    }
}
