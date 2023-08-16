package com.example.employeemanagementsystem.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity
@Table(name = "COM_EMPLOYEEMGMT_EMPLOYEE_MST")
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Employee extends Auditable {
    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotNull
    @Column(unique = true, name = "EMPLOYEE_CODE")
    private String uniqueEmployeeCode;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Email
    @Column(name = "EMAIL_ID")
    private String emailId;

    @NotNull
    @Email
    @Column(name = "ALTERNATE_EMAIL_ID", nullable = false)
    private String alternativeEmailId;

    @Digits(
            integer = 10,
            fraction = 0,
            message = "Mobile number is to be of 10 digits"
    )
    @NotNull
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @NotNull
    @Size(min = 1, max = 1, message = "Activity Flag has to be Y or N")
    @Column(name = "ACTIVE_STATUS")
    private String activeStatus;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    private Set<AttendanceEntity> attendances;
}


