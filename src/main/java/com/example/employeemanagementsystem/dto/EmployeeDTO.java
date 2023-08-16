package com.example.employeemanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class EmployeeDTO {
    @Id
    @Column(name = "EMPLOYEE_ID")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long employeeId;

    @NotNull
    @Column(unique = true, name = "EMPLOYEE_CODE")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uniqueEmployeeCode;

    @Column(name = "FIRST_NAME")
    @NotNull
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Email
    @Column( name = "EMAIL_ID")
    private String emailId;

    @NotNull
    @Email
    @Column(name = "ALTERNATE_EMAIL_ID" , nullable = false)
    private String alternativeEmailId;

    @NotNull
    @Digits(
            integer = 10,
            fraction = 0,
            message = "Mobile number is to be of 10 digits"
    )
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @NotNull
    @Size(min = 1, max = 1, message = "Activity Flag has to be Y or N")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "ACTIVE_STATUS")
    private String activeStatus;

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", uniqueEmployeeCode='" + uniqueEmployeeCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", alternativeEmailId='" + alternativeEmailId + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                '}';
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long employeeId, String uniqueEmployeeCode, String firstName, String lastName, String emailId, String alternativeEmailId, String mobileNumber, @NotNull String activeStatus) {
        this.employeeId = employeeId;
        this.uniqueEmployeeCode = uniqueEmployeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.alternativeEmailId = alternativeEmailId;
        this.mobileNumber = mobileNumber;
        this.activeStatus = activeStatus;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getUniqueEmployeeCode() {
        return uniqueEmployeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAlternativeEmailId() {
        return alternativeEmailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setUniqueEmployeeCode(String uniqueEmployeeCode) {
        this.uniqueEmployeeCode = uniqueEmployeeCode;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setAlternativeEmailId(String alternativeEmailId) {
        this.alternativeEmailId = alternativeEmailId;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
}
