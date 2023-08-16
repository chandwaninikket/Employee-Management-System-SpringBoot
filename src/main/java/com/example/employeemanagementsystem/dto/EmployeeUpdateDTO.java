package com.example.employeemanagementsystem.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeUpdateDTO {

    @Email
    @Column(name = "EMAIL_ID")
    private String emailId;

    @Email
    @Column(name = "ALTERNATE_EMAIL_ID")
    private String alternativeEmailId;

    @NotNull
    @Size(min = 1, max = 1, message = "Activity Flag has to be Y or N")
    @Column(name = "ACTIVE_STATUS")
    private String activeStatus;
}
