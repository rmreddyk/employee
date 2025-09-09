package com.example.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "employee")
public class Employee {

    private static final Logger logger = LoggerFactory.getLogger(Employee.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String department;
    private String address;
    private LocalDate dob;
    private LocalDate doj;
    private String designation;

    public Employee() {}

    public Employee(Long id, String firstName, String lastName, String email, String department, String address, LocalDate dob, LocalDate doj, String designation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.address = address;
        this.dob = dob;
        this.doj = doj;
        this.designation = designation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        logger.debug("Setting employee ID: {}", id);
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        logger.debug("Setting employee firstName: {}", firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        logger.debug("Setting employee lastName: {}", lastName);
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        logger.debug("Setting employee email: {}", email);
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        logger.debug("Setting employee department: {}", department);
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        logger.debug("Setting employee address: {}", address);
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        logger.debug("Setting employee dob: {}", dob);
        this.dob = dob;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        logger.debug("Setting employee doj: {}", doj);
        this.doj = doj;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        logger.debug("Setting employee designation: {}", designation);
        this.designation = designation;
    }


}