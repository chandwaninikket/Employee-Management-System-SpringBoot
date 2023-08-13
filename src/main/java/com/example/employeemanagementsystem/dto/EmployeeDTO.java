package com.example.employeemanagementsystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class EmployeeDTO {
    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(unique = true, name = "EMPLOYEE_CODE")
    private String uniqueEmployeeCode;

    @Column(nullable = false, name = "FIRST_NAME")
    private String firstName;

    @Column(nullable = false, name = "LAST_NAME")
    private String lastName;

    @Email
    @Column(nullable = false, name = "EMAIL_ID")
    private String emailId;

    @Email
    @Column(nullable = false, name = "ALTERNATE_EMAIL_ID")
    private String alternativeEmailId;

    @Digits(
            integer = 10,
            fraction = 0,
            message = "Mobile number is to be of 10 digits"
    )
    @Column(nullable = false, name = "MOBILE_NUMBER")
    private String mobileNumber;

    @NotNull
    @Size(min = 1, max = 1, message = "Activity Flag has to be Y or N")
    @Column(name = "ACTIVE_STATUS")
    private String activeStatus;


}
