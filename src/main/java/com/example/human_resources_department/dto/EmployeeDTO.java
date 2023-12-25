package com.example.human_resources_department.dto;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String phone;
    private String email;
    private String recruiterName;
    private Set<Role> employeeRoles;
    private Date dateOfRegistration;
    private Boolean active;
    private String filePhoto;
    private String secretCodeForRole;

    public EmployeeDTO(
            Long id,
            String firstName,
            String secondName,
            String lastName,
            String phone,
            String email,
            String recruiterName,
            Set<Role> employeeRoles,
            Date dateOfRegistration,
            Boolean active,
            String filePhoto,
            String secretCodeForRole
    ) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.recruiterName = recruiterName;
        this.employeeRoles = employeeRoles;
        this.dateOfRegistration = dateOfRegistration;
        this.active = active;
        this.filePhoto = filePhoto;
        this.secretCodeForRole = secretCodeForRole;
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public Set<Role> getEmployeeRoles() {
        return employeeRoles;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public Boolean isActive() {
        return active;
    }

    public String getFilePhoto() {
        return filePhoto;
    }

    public String getSecretCodeForRole() {
        return secretCodeForRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return active == that.active &&
                Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) && Objects.equals(lastName, that.lastName) &&
                Objects.equals(phone, that.phone) && Objects.equals(email, that.email) &&
                Objects.equals(recruiterName, that.recruiterName) &&
                Objects.equals(employeeRoles, that.employeeRoles) &&
                Objects.equals(dateOfRegistration, that.dateOfRegistration) &&
                Objects.equals(filePhoto, that.filePhoto) && Objects.equals(secretCodeForRole, that.secretCodeForRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, firstName, secondName, lastName, phone, email, recruiterName,
                employeeRoles, dateOfRegistration, active, filePhoto, secretCodeForRole
        );
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", recruiterName='" + recruiterName + '\'' +
                ", employeeRoles=" + employeeRoles +
                ", dateOfRegistration=" + dateOfRegistration +
                ", active=" + active +
                ", filePhoto='" + filePhoto + '\'' +
                ", secretCodeForRole='" + secretCodeForRole + '\'' +
                '}';
    }

    public static EmployeeDTO fromEmployeeDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getSecondName(),
                employee.getLastName(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getRecruiterName(),
                employee.getEmployeeRoles(),
                employee.getDateOfRegistration(),
                employee.getActive(),
                employee.getFilePhoto(),
                employee.getSecretCodeForRole()
        );
    }

    public static Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(employee.getId());
        employee.setFirstName(employee.getFirstName());
        employee.setSecondName(employee.getSecondName());
        employee.setLastName(employee.getLastName());
        employee.setPhone(employee.getPhone());
        employee.setEmail(employee.getEmail());
        employee.setRecruiter(employee.getRecruiter());
        employee.setEmployeeRoles(employee.getEmployeeRoles());
        employee.setDateOfRegistration(employee.getDateOfRegistration());
        employee.setActive(employee.getActive());
        employee.setFilePhoto(employee.getFilePhoto());
        employee.setSecretCodeForRole(employee.getSecretCodeForRole());
        return employee;
    }

}