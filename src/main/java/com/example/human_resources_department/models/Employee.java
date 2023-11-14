package com.example.human_resources_department.models;


import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    private String phone;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recruiter_id")
    private User recruiter;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING) //зберігаємо у вигляді строки до БД
    private Set<Role> employeeRoles = new HashSet<>();

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "reg_date", nullable = false)
    private Date dateOfRegistration;

    private boolean active; //or released
    private String filePhoto;
    @Column(name = "secret_code_for_role")
    private String secretCodeForRole;

    public Employee() {
    }

    public Employee(
            String firstName, String secondName,
            String lastName, String phone,
            String email, User recruiter
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.recruiter = recruiter;
    }

    public String getRecruiterName() { //if recruiter will be
        return recruiter != null ? recruiter.getUsername() : "<unknown>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void generateSecretCode() {
        secretCodeForRole = UUID.randomUUID().toString().replace("-", "");
    }

    public String getSecretCodeForRole() {
        return secretCodeForRole;
    }

    public void setSecretCodeForRole(String secretCodeForRole) {
        this.secretCodeForRole = secretCodeForRole;
    }

    public Set<Role> getEmployeeRoles() {
        return employeeRoles;
    }

    public void setEmployeeRoles(Set<Role> employeeRoles) {
        this.employeeRoles = employeeRoles;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public User getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(User recruiter) {
        this.recruiter = recruiter;
    }

    public String getFilePhoto() {
        return filePhoto;
    }

    public void setFilePhoto(String filePhoto) {
        this.filePhoto = filePhoto;
    }
}
